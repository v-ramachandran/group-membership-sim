package edu.utexas.cs.systems.membership.simulator.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.Gson;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.controller.NetController;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessage;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessageEnvelope;
import edu.utexas.cs.systems.membership.simulator.network.message.SerializedMessageEnvelope;
import edu.utexas.cs.systems.membership.simulator.network.predicate.DisjunctionPredicate;
import edu.utexas.cs.systems.membership.simulator.network.predicate.Predicate;

public class SimpleNetworkCommunicator implements AbortingNetworkCommunicator {

    private NetController netController;
    private MemberIdentification selfIdentity;
    private Set<MemberIdentification> memberIds;
    private Gson gson;
    private DisjunctionPredicate<Table<Integer, MessageType, Integer>> sendAbortPolicy;
    private Table<Integer, MessageType, Integer> sendLog;

    public SimpleNetworkCommunicator(final NetController netController, 
        final MemberIdentification selfIdentity, 
        final Set<MemberIdentification> memberIds, 
        final Gson gson) {

        this.netController = netController;
        this.selfIdentity = selfIdentity;
        this.memberIds = memberIds;
        this.gson = gson;
        this.sendLog = HashBasedTable.create();
        this.sendAbortPolicy = new DisjunctionPredicate<Table<Integer,MessageType,Integer>>();
    }
    
    private void incrementValueInTable(final Table<Integer, MessageType, Integer> table,
        final int memberId, final MessageType messageType) {
        
        if (table.contains(memberId, messageType)) {
            int newValue = table.get(memberId, messageType) + 1;
            table.put(memberId, messageType, newValue);
        } else {
            table.put(memberId, messageType, 1);
        }
    }
    
    private <Message> void createAndSendNetworkMessage(final Message message,
        final MemberIdentification destination) {
        
        final MessageType messageType = 
            MessageType.retrieveMatchingType(message.getClass());
        final String messagePayload = gson.toJson(message);
        final NetworkMessage networkMessage = NetworkMessage.builder()
            .setMessagePayload(messagePayload)
            .setMessageType(messageType)
            .setRecipient(destination)
            .setSender(selfIdentity)
            .build();

        incrementValueInTable(sendLog, destination.getId(), messageType);

        final String payload = gson.toJson(networkMessage);
        final int destinationId = destination.getId();
        netController.sendMsg(destinationId, payload);

        if (sendAbortPolicy.isSatisfied(sendLog)) {
            throw new ScriptedNetworkCrashException();
        }
    }
    
    @Override
    public <Message> void broadcastMessage(final Message networkMessage) {
        for (MemberIdentification destination : memberIds) {
            createAndSendNetworkMessage(networkMessage, destination);
        }
    }

    @Override
    public <Message> void unicastMessage(final Message networkMessage,
            MemberIdentification destination) {
        createAndSendNetworkMessage(networkMessage, destination);
    }

    @Override
    public List<NetworkMessageEnvelope> flushIncomingMessages() {
        final Iterable<String> receivedMessages = netController.getReceivedMsgs();
        final List<NetworkMessageEnvelope> messageEnvelopesToReturn = 
            new ArrayList<NetworkMessageEnvelope>();
        
        for(final String receivedMessage : receivedMessages) {
            final NetworkMessage networkMessage = gson.
                fromJson(receivedMessage, NetworkMessage.class);
            final NetworkMessageEnvelope networkMessageEnvelope =
                new SerializedMessageEnvelope(networkMessage, gson);
            messageEnvelopesToReturn.add(networkMessageEnvelope);
        }
        return messageEnvelopesToReturn;
    }

    @Override
    public <Message> void unicastToSelf(final Message networkMessage) {
        createAndSendNetworkMessage(networkMessage, selfIdentity);
    }

    @Override
    public void shutdown() {
        netController.shutdown();
    }

    @Override
    public MemberIdentification getSelf() {
        return selfIdentity;
    }

    @Override
    public void addSendMessageAbortCondition(
        final Predicate<Table<Integer, MessageType, Integer>> condition) {
        sendAbortPolicy.addCondition(condition);
    }
}
