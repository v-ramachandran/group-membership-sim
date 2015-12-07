package edu.utexas.cs.systems.membership.simulator.network.message;

import com.google.auto.value.AutoValue;

import edu.utexas.cs.systems.membership.simulator.network.AutoGson;

/**
 * The payload for a Present message. This doesn't particularly have any information
 * in it at this point. However, this remains as an empty blob for future flexibility.
 */
@AutoValue
@AutoGson(autoValueClass = AutoValue_PresentMessage.class)
public abstract class PresentMessage {
    public abstract long getTimestamp();

    @AutoValue.Builder
    public static abstract class PresentMessageBuilder {
        public abstract PresentMessageBuilder setTimestamp(final long timestamp);
        public abstract PresentMessage build();
    }

    public static PresentMessageBuilder builder() {
        return new AutoValue_PresentMessage.Builder()
            .setTimestamp(0L);
    }
}
