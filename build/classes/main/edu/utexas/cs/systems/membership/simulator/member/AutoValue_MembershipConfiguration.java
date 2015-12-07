
package edu.utexas.cs.systems.membership.simulator.member;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_MembershipConfiguration extends MembershipConfiguration {

  private final long periodLengthMillis;
  private final long broadcastLatencyMillis;
  private final long initialStartMillis;
  private final long schedulingDelayMillis;

  private AutoValue_MembershipConfiguration(
      long periodLengthMillis,
      long broadcastLatencyMillis,
      long initialStartMillis,
      long schedulingDelayMillis) {
    this.periodLengthMillis = periodLengthMillis;
    this.broadcastLatencyMillis = broadcastLatencyMillis;
    this.initialStartMillis = initialStartMillis;
    this.schedulingDelayMillis = schedulingDelayMillis;
  }

  @Override
  public long getPeriodLengthMillis() {
    return periodLengthMillis;
  }

  @Override
  public long getBroadcastLatencyMillis() {
    return broadcastLatencyMillis;
  }

  @Override
  public long getInitialStartMillis() {
    return initialStartMillis;
  }

  @Override
  public long getSchedulingDelayMillis() {
    return schedulingDelayMillis;
  }

  @Override
  public String toString() {
    return "MembershipConfiguration{"
        + "periodLengthMillis=" + periodLengthMillis + ", "
        + "broadcastLatencyMillis=" + broadcastLatencyMillis + ", "
        + "initialStartMillis=" + initialStartMillis + ", "
        + "schedulingDelayMillis=" + schedulingDelayMillis
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MembershipConfiguration) {
      MembershipConfiguration that = (MembershipConfiguration) o;
      return (this.periodLengthMillis == that.getPeriodLengthMillis())
           && (this.broadcastLatencyMillis == that.getBroadcastLatencyMillis())
           && (this.initialStartMillis == that.getInitialStartMillis())
           && (this.schedulingDelayMillis == that.getSchedulingDelayMillis());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= (periodLengthMillis >>> 32) ^ periodLengthMillis;
    h *= 1000003;
    h ^= (broadcastLatencyMillis >>> 32) ^ broadcastLatencyMillis;
    h *= 1000003;
    h ^= (initialStartMillis >>> 32) ^ initialStartMillis;
    h *= 1000003;
    h ^= (schedulingDelayMillis >>> 32) ^ schedulingDelayMillis;
    return h;
  }

  static final class Builder extends MembershipConfiguration.MembershipConfigurationBuilder {
    private Long periodLengthMillis;
    private Long broadcastLatencyMillis;
    private Long initialStartMillis;
    private Long schedulingDelayMillis;
    Builder() {
    }
    Builder(MembershipConfiguration source) {
      this.periodLengthMillis = source.getPeriodLengthMillis();
      this.broadcastLatencyMillis = source.getBroadcastLatencyMillis();
      this.initialStartMillis = source.getInitialStartMillis();
      this.schedulingDelayMillis = source.getSchedulingDelayMillis();
    }
    @Override
    public MembershipConfiguration.MembershipConfigurationBuilder setPeriodLengthMillis(long periodLengthMillis) {
      this.periodLengthMillis = periodLengthMillis;
      return this;
    }
    @Override
    public MembershipConfiguration.MembershipConfigurationBuilder setBroadcastLatencyMillis(long broadcastLatencyMillis) {
      this.broadcastLatencyMillis = broadcastLatencyMillis;
      return this;
    }
    @Override
    public MembershipConfiguration.MembershipConfigurationBuilder setInitialStartMillis(long initialStartMillis) {
      this.initialStartMillis = initialStartMillis;
      return this;
    }
    @Override
    public MembershipConfiguration.MembershipConfigurationBuilder setSchedulingDelayMillis(long schedulingDelayMillis) {
      this.schedulingDelayMillis = schedulingDelayMillis;
      return this;
    }
    @Override
    public MembershipConfiguration build() {
      String missing = "";
      if (periodLengthMillis == null) {
        missing += " periodLengthMillis";
      }
      if (broadcastLatencyMillis == null) {
        missing += " broadcastLatencyMillis";
      }
      if (initialStartMillis == null) {
        missing += " initialStartMillis";
      }
      if (schedulingDelayMillis == null) {
        missing += " schedulingDelayMillis";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_MembershipConfiguration(
          this.periodLengthMillis,
          this.broadcastLatencyMillis,
          this.initialStartMillis,
          this.schedulingDelayMillis);
    }
  }
}
