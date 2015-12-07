package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;

import edu.utexas.cs.systems.membership.simulator.logs.LogItem;
import edu.utexas.cs.systems.membership.simulator.logs.LogSet;
import edu.utexas.cs.systems.membership.simulator.member.CircularOrderedMemberList;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.member.MembershipConfiguration;
import edu.utexas.cs.systems.membership.simulator.member.OrderedMemberList;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessageEnvelope;
import edu.utexas.cs.systems.membership.simulator.network.message.NewGroupMessage;
import edu.utexas.cs.systems.membership.simulator.network.message.PresentMessage;

public class PeriodicBroadcastGroupMemberStrategy implements MembershipStrategy {

    private AbortingNetworkCommunicator networkCommunicator;
    private OrderedMemberList orderedMemberList;
    private ScheduledFuture<?> broadcastFuture;
    private ScheduledFuture<?> membershipCheckFuture;
    private ListeningScheduledExecutorService scheduledExecutorService;
    private PeriodicPresentBroadcast periodicPresentBroadcast;
    private LogSet logSet;

    public PeriodicBroadcastGroupMemberStrategy(final AbortingNetworkCommunicator networkCommunicator,
        final Iterable<MemberIdentification> memberIds, final LogSet logSet) {

        this.logSet = logSet;
        this.networkCommunicator = networkCommunicator;
        this.orderedMemberList = new CircularOrderedMemberList.Builder()
            .addMembers(memberIds)
            .build();

        this.broadcastFuture = null;
        this.membershipCheckFuture = null;
        this.scheduledExecutorService = 
            MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(4));
        this.periodicPresentBroadcast = null;
    }

    @Override
    public GroupMemberState execute(final GroupMemberState currentState) {

        if (currentState.getGroupMembershipId() == 0 && networkCommunicator.getSelf().getId() == 0) {
            initiate(currentState);
            
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {}
        }
        
        final List<NetworkMessageEnvelope> networkMessageEnvelopes = 
            networkCommunicator.flushIncomingMessages();
        GroupMemberState groupMemberState = currentState;
        for (final NetworkMessageEnvelope networkMessageEnvelope : networkMessageEnvelopes) {
            final MessageType messageType = networkMessageEnvelope.peekAtMessageType();
            if (MessageType.NEW_GROUP.equals(messageType)) {
                groupMemberState = handleNewGroup(networkMessageEnvelope.open(NewGroupMessage.class), groupMemberState);
            } else if (MessageType.PRESENT.equals(messageType)) {
                groupMemberState = handlePresent(networkMessageEnvelope, groupMemberState);
            } else if (MessageType.MEMBER_DROPPED.equals(messageType)) {
                groupMemberState = handleMemberDropped(networkMessageEnvelope, groupMemberState);
            }
        }
        return groupMemberState;
    }

    private GroupMemberState handleMemberDropped(final NetworkMessageEnvelope envelope,
        final GroupMemberState currentState) {

        final int periodsCompleted = currentState.getPeriodsCompleted();
        final long nextGroupId = currentState.getGroupMembershipId() + 
            (periodsCompleted*currentState.getMembershipConfiguration().getPeriodLengthMillis());
        final Iterable<Integer> memberIds = orderedMemberList.removeAbsentMembers();
        LogItem missingMembersLog = LogItem.builder()
            .setGroupId(currentState.getGroupMembershipId())
            .setPeriod(currentState.getPeriodsCompleted())
            .setProcessId(envelope.getRecipient().getId())
            .setEventTimestamp(System.currentTimeMillis())
            .didNotReceive(MessageType.PRESENT, memberIds)
            .build();
        logSet.addLogItem(missingMembersLog);
        orderedMemberList.resetAttendance();
        periodicPresentBroadcast.updateGroupId(nextGroupId);
        LogItem newGroupLog = LogItem.builder()
            .setGroupId(nextGroupId)
            .setPeriod(currentState.getPeriodsCompleted())
            .setProcessId(envelope.getRecipient().getId())
            .setEventTimestamp(System.currentTimeMillis())
            .formedANewGroup(nextGroupId, orderedMemberList.getAllMembers())
            .build();
            logSet.addLogItem(newGroupLog);
        return GroupMemberState.builder(currentState)
            .setGroupMembershipId(nextGroupId)
            .build();
    }

    @Override
    public GroupMemberState initiate(GroupMemberState initialState) {

        final MembershipConfiguration membershipConfiguration = 
            initialState.getMembershipConfiguration();
        final long groupId = membershipConfiguration.getInitialStartMillis() 
            + membershipConfiguration.getBroadcastLatencyMillis();
        final NewGroupMessage newGroupMessage = NewGroupMessage.builder()
            .setTimestamp(groupId)
            .build();
        networkCommunicator.broadcastMessage(newGroupMessage);

        return GroupMemberState.builder(initialState)
            .build();
    }

    private GroupMemberState handlePresent(final NetworkMessageEnvelope envelope, 
        final GroupMemberState groupMemberState) {
        
        final MemberIdentification memberIdentification = envelope.getSender();
        orderedMemberList.markAsPresent(memberIdentification.getId());
        final long periodCompletedAppliedToTime = groupMemberState.getGroupMembershipId() + 
            (groupMemberState.getPeriodsCompleted() * 
                groupMemberState.getMembershipConfiguration().getPeriodLengthMillis());
        if(!orderedMemberList.hasAbsentMembers() && periodCompletedAppliedToTime <= System.currentTimeMillis()) {
            LogItem allPresentLog = LogItem.builder()
                .setGroupId(groupMemberState.getGroupMembershipId())
                .setPeriod(groupMemberState.getPeriodsCompleted())
                .setProcessId(envelope.getRecipient().getId())
                .setEventTimestamp(System.currentTimeMillis())
                .didReceive(MessageType.PRESENT, orderedMemberList.getAllMembers())
                .build();
            logSet.addLogItem(allPresentLog);
            return GroupMemberState.builder(groupMemberState)
                .setPeriodsCompleted(groupMemberState.getPeriodsCompleted() + 1)
                .build();
        } else {
            return groupMemberState;
        }
    }

    private GroupMemberState handleNewGroup(final NewGroupMessage newGroupMessage, 
        final GroupMemberState groupMemberState) {

        final long groupId = newGroupMessage.getTimestamp();

        // broadcast presence
        final PresentMessage presentMessage = PresentMessage.builder()
            .setTimestamp(groupMemberState.getGroupMembershipId())
            .build();
        networkCommunicator.broadcastMessage(presentMessage);

        // schedule periodic broadcast of presence
        periodicPresentBroadcast = new PeriodicPresentBroadcast(networkCommunicator, logSet, groupId);
        final long period = groupMemberState.getMembershipConfiguration().getPeriodLengthMillis();
        final long schedulingDelay = 
            groupMemberState.getMembershipConfiguration().getSchedulingDelayMillis();
        final long initialDelay = groupId + period - schedulingDelay - System.currentTimeMillis();
        broadcastFuture = scheduledExecutorService.scheduleAtFixedRate(
            periodicPresentBroadcast, initialDelay, period, TimeUnit.MILLISECONDS);
        
        // schedule periodic membership check 
        final long broadcastDelay = 
            groupMemberState.getMembershipConfiguration().getBroadcastLatencyMillis();
        final PeriodicBroadcastMembershipCheck periodicBroadcastMembershipCheck = 
            new PeriodicBroadcastMembershipCheck(networkCommunicator, orderedMemberList, logSet);
        membershipCheckFuture = scheduledExecutorService.scheduleAtFixedRate(
            periodicBroadcastMembershipCheck, initialDelay + broadcastDelay, period, TimeUnit.MILLISECONDS); 
        return GroupMemberState.builder(groupMemberState) 
            .setGroupMembershipId(groupId)
            .build();
    }

    @Override
    public void shutdown() {
        if (broadcastFuture != null && !broadcastFuture.isDone()) {
            broadcastFuture.cancel(true);
        }

        if (membershipCheckFuture != null && !membershipCheckFuture.isDone()) {
            membershipCheckFuture.cancel(true);
        }

        scheduledExecutorService.shutdown();
    }
}
