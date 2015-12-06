package edu.utexas.cs.systems.membership.simulator.network.message;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;

public interface NetworkMessageEnvelope {

    public MemberIdentification getSender();

    public MemberIdentification getRecipient();

    public MessageType peekAtMessageType();

    public <Message> Message open(final Class<Message> classObject);
}
