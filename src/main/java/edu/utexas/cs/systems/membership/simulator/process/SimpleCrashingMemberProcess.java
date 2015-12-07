package edu.utexas.cs.systems.membership.simulator.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import edu.utexas.cs.systems.membership.simulator.logs.LogSet;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStrategyLabel;
import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.ScriptedNetworkCrashException;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.predicate.Predicate;
import edu.utexas.cs.systems.membership.simulator.strategy.verify.MembershipStrategy;
import edu.utexas.cs.systems.membership.simulator.strategy.verify.PeriodicBroadcastGroupMemberStrategy;

public class SimpleCrashingMemberProcess implements CrashingMemberProcess {

    private AbortingNetworkCommunicator networkCommunicator;
    private GroupMemberState groupMemberState;
    private Map<GroupMemberStrategyLabel, MembershipStrategy> membershipStrategyMap;
    private LogSet logSet;
    
    private volatile boolean alive;
    private int numberTimesToRun;

    public SimpleCrashingMemberProcess(final AbortingNetworkCommunicator networkCommunicator,
        final GroupMemberState initialState, final Iterable<MemberIdentification> memberIds,
        final LogSet logSet) {

        this.networkCommunicator = networkCommunicator;
        this.groupMemberState = initialState;
        this.alive = false;
        this.numberTimesToRun = 0;
        this.logSet = logSet;

        this.membershipStrategyMap = new HashMap<GroupMemberStrategyLabel, MembershipStrategy>();
        this.membershipStrategyMap.put(GroupMemberStrategyLabel.PERIODIC_BROADCAST, 
            new PeriodicBroadcastGroupMemberStrategy(networkCommunicator, memberIds, logSet));
    }

    @Override
    public void initiate() {
        final GroupMemberStrategyLabel strategyLabel = groupMemberState.getGroupMemberStateLabel();
        if (membershipStrategyMap.containsKey(strategyLabel)) {
            this.groupMemberState = 
                membershipStrategyMap.get(strategyLabel).initiate(groupMemberState);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void updateNumberTimesToRun(int numberTimesToRun) {
        this.numberTimesToRun = numberTimesToRun;
    }

    @Override
    public void run() {
        this.alive = true;

        int lastCompletedPeriod = 0;
        while(this.alive && groupMemberState.getPeriodsCompleted() < numberTimesToRun) {
            final GroupMemberStrategyLabel strategyLabel = groupMemberState.getGroupMemberStateLabel();
            if (membershipStrategyMap.containsKey(strategyLabel)) {
                try {
                    this.groupMemberState = 
                        membershipStrategyMap.get(strategyLabel).execute(groupMemberState);
                } catch (ScriptedNetworkCrashException exception) {
                    System.out.println(String.format("%s performed a scripted crash.", 
                        networkCommunicator.getSelf()));
                    break;
                } catch (Exception exception) {
                    break;
                }
                
                final int periodsCompleted = groupMemberState.getPeriodsCompleted();
                if (periodsCompleted > lastCompletedPeriod) {
                    System.out.println(String.format("%s completed period/round %s at %s.", 
                        networkCommunicator.getSelf(), lastCompletedPeriod, System.currentTimeMillis()));
                    lastCompletedPeriod++;
                }
            } else {
                throw new UnsupportedOperationException();
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {}
        }
        
        if (groupMemberState.getPeriodsCompleted() >= numberTimesToRun) {
            System.out.println(String.format("%s completed the scheduled periods/rounds.", 
                    networkCommunicator.getSelf()));
        }
        
        this.alive = false;
        cleanUp();
    }

    @Override
    public void killProcess() {
        this.alive = false;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public void cleanUp() {
        networkCommunicator.shutdown();
        for (Entry<GroupMemberStrategyLabel, MembershipStrategy> entry : membershipStrategyMap.entrySet()) {
            entry.getValue().shutdown();
        }
    }

    @Override
    public void addSendMessageAbortCondition(
            Predicate<Table<Integer, MessageType, Integer>> condition) {
        networkCommunicator.addSendMessageAbortCondition(condition);
    }
}
