package edu.utexas.cs.systems.membership.simulator.network.message;

import java.io.Serializable;

import com.google.auto.value.AutoValue;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.AutoGson;

/**
 * The payload for a New-Group message. 
 */
@AutoValue
@AutoGson(autoValueClass = AutoValue_NewGroupMessage.class)
public abstract class NewGroupMessage implements Serializable {
    
    public abstract long getTimestamp();

    @AutoValue.Builder
    protected static abstract class NewGroupMessageBuilder {
        public abstract NewGroupMessageBuilder setTimestamp(final long timestamp);
        public abstract NewGroupMessage build();
    }

    public static NewGroupMessageBuilder builder() {
        return new AutoValue_NewGroupMessage.Builder();
    }
}
