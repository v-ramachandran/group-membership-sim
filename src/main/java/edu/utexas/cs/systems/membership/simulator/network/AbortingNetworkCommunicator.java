package edu.utexas.cs.systems.membership.simulator.network;

import java.util.List;

import com.google.common.collect.Table;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessageEnvelope;
import edu.utexas.cs.systems.membership.simulator.network.predicate.Predicate;

public interface AbortingNetworkCommunicator {

    public <Message> void broadcastMessage(final Message networkMessage);

    public <Message> void unicastMessage(final Message networkMessage, 
        final MemberIdentification destination);

    public List<NetworkMessageEnvelope> flushIncomingMessages();

    public <Message> void unicastToSelf(final Message networkMessage);

    public void shutdown();

    public MemberIdentification getSelf();

    public void addSendMessageAbortCondition(
        final Predicate<Table<Integer, MessageType, Integer>> condition);
}
