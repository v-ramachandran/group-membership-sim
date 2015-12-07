package edu.utexas.cs.systems.membership.simulator.network.message;

import com.google.auto.value.AutoValue;

import edu.utexas.cs.systems.membership.simulator.network.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_MemberDroppedMessage.class)
public abstract class MemberDroppedMessage {
    
    @AutoValue.Builder
    public static abstract class MemberDroppedMessageBuilder {
        public abstract MemberDroppedMessage build();
    }
    
    public static MemberDroppedMessageBuilder builder() {
        return new AutoValue_MemberDroppedMessage.Builder();
    }
}
