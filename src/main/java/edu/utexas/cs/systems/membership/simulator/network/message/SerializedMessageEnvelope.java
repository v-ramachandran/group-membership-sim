package edu.utexas.cs.systems.membership.simulator.network.message;

import com.google.gson.Gson;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;

public class SerializedMessageEnvelope implements NetworkMessageEnvelope {

    private NetworkMessage networkMessage;
    private Gson gson;
    
    public SerializedMessageEnvelope(final NetworkMessage networkMessage,
        final Gson gson) {
        
        this.networkMessage = networkMessage;
        this.gson = gson;
    }
    
    @Override
    public MemberIdentification getSender() {
        return networkMessage.getSender();
    }

    @Override
    public MemberIdentification getRecipient() {
        return networkMessage.getRecipient();
    }

    @Override
    public MessageType peekAtMessageType() {
        return networkMessage.getMessageType();
    }

    @Override
    public <Message> Message open(Class<Message> classObject) {
        final String messagePayload = networkMessage.getMessagePayload();
        return gson.fromJson(messagePayload, classObject);
    }
}
