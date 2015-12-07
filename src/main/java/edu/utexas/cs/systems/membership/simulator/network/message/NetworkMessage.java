package edu.utexas.cs.systems.membership.simulator.network.message;

import com.google.auto.value.AutoValue;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_NetworkMessage.class)
public abstract class NetworkMessage {

    public abstract MemberIdentification getSender();
    public abstract MemberIdentification getRecipient();
    public abstract String getMessagePayload();
    public abstract MessageType getMessageType();

    @AutoValue.Builder
    public static abstract class NetworkMessageBuilder {
        public abstract NetworkMessageBuilder setSender(
            final MemberIdentification memberIdentification);
        public abstract NetworkMessageBuilder setRecipient(
            final MemberIdentification memberIdentification);
        public abstract NetworkMessageBuilder setMessagePayload(final String messagePayload);
        public abstract NetworkMessageBuilder setMessageType(final MessageType messageType);

        public abstract NetworkMessage build();
    }

    public static NetworkMessageBuilder builder() {
        return new AutoValue_NetworkMessage.Builder();
    }
}
