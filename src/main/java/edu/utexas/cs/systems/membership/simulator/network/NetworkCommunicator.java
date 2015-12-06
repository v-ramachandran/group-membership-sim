package edu.utexas.cs.systems.membership.simulator.network;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessageEnvelope;

public interface NetworkCommunicator {

    public <Message> void broadcastMessage(final Message networkMessage);

    public <Message> void unicastMessage(final Message networkMessage, 
        final MemberIdentification destination);

    public Iterable<NetworkMessageEnvelope> flushIncomingMessages();

    public void shutdown();
}
