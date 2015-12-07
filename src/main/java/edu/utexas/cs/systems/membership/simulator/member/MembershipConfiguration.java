package edu.utexas.cs.systems.membership.simulator.member;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MembershipConfiguration {

    public abstract long getPeriodLengthMillis();
    public abstract long getBroadcastLatencyMillis();
    public abstract long getInitialStartMillis();
    public abstract long getSchedulingDelayMillis();
    
    @AutoValue.Builder
    public abstract static class MembershipConfigurationBuilder {
        public abstract MembershipConfigurationBuilder 
            setPeriodLengthMillis(final long periodLengthMillis);
        public abstract MembershipConfigurationBuilder 
            setBroadcastLatencyMillis(final long broadcastLatencyMillis);
        public abstract MembershipConfigurationBuilder
            setInitialStartMillis(final long initialStartMillis);
        public abstract MembershipConfigurationBuilder
            setSchedulingDelayMillis(final long schedulingDelayMillis);
        public abstract MembershipConfiguration build();
    }

    public static MembershipConfigurationBuilder builder() {
        return new AutoValue_MembershipConfiguration.Builder();
    }

    public static MembershipConfigurationBuilder builder(final MembershipConfiguration prototype) {
        return new AutoValue_MembershipConfiguration.Builder()
            .setBroadcastLatencyMillis(prototype.getBroadcastLatencyMillis())
            .setInitialStartMillis(prototype.getInitialStartMillis())
            .setPeriodLengthMillis(prototype.getPeriodLengthMillis())
            .setSchedulingDelayMillis(prototype.getSchedulingDelayMillis());
    }

    public static MembershipConfiguration defaultConfiguration() {
        return new AutoValue_MembershipConfiguration.Builder()
            .setBroadcastLatencyMillis(4000l)
            .setInitialStartMillis(System.currentTimeMillis())
            .setPeriodLengthMillis(10000l)
            .setSchedulingDelayMillis(1000l)
            .build();
    }
}
