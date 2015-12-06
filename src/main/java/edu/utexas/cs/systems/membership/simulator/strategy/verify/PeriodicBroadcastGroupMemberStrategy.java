package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.network.NetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessageEnvelope;
import edu.utexas.cs.systems.membership.simulator.network.message.NewGroupMessage;
import edu.utexas.cs.systems.membership.simulator.network.message.PresentMessage;

public class PeriodicBroadcastGroupMemberStrategy implements MembershipVerificationStrategy {

    private NetworkCommunicator networkCommunicator;

    public PeriodicBroadcastGroupMemberStrategy(final NetworkCommunicator networkCommunicator) {
        this.networkCommunicator = networkCommunicator;
    }

    @Override
    public GroupMemberState execute(final GroupMemberState currentState) {
        
        final Iterable<NetworkMessageEnvelope> networkMessageEnvelopes = 
            networkCommunicator.flushIncomingMessages();

        for (final NetworkMessageEnvelope networkMessageEnvelope : networkMessageEnvelopes) {

            final MessageType messageType = networkMessageEnvelope.peekAtMessageType();
            if (MessageType.NEW_GROUP.equals(messageType)) {
                joinNewGroup(networkMessageEnvelope.open(NewGroupMessage.class), currentState);
            } else if (MessageType.PRESENT.equals(messageType)) {
                markAttendance(networkMessageEnvelope.open(PresentMessage.class), currentState);
            } 
        }
        return currentState;
    }

    private void markAttendance(final PresentMessage presentMessage, 
        final GroupMemberState groupMemberState) {
    }

    private void joinNewGroup(NewGroupMessage newGroupMessage,
        final GroupMemberState groupMemberState) {
    }
}
