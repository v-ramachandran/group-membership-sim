
package edu.utexas.cs.systems.membership.simulator.member;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroupMemberState extends GroupMemberState {

  private final GroupMemberStrategyLabel groupMemberStateLabel;
  private final MembershipConfiguration membershipConfiguration;
  private final Long groupMembershipId;
  private final Integer periodsCompleted;

  private AutoValue_GroupMemberState(
      GroupMemberStrategyLabel groupMemberStateLabel,
      MembershipConfiguration membershipConfiguration,
      Long groupMembershipId,
      Integer periodsCompleted) {
    if (groupMemberStateLabel == null) {
      throw new NullPointerException("Null groupMemberStateLabel");
    }
    this.groupMemberStateLabel = groupMemberStateLabel;
    if (membershipConfiguration == null) {
      throw new NullPointerException("Null membershipConfiguration");
    }
    this.membershipConfiguration = membershipConfiguration;
    if (groupMembershipId == null) {
      throw new NullPointerException("Null groupMembershipId");
    }
    this.groupMembershipId = groupMembershipId;
    if (periodsCompleted == null) {
      throw new NullPointerException("Null periodsCompleted");
    }
    this.periodsCompleted = periodsCompleted;
  }

  @Override
  public GroupMemberStrategyLabel getGroupMemberStateLabel() {
    return groupMemberStateLabel;
  }

  @Override
  public MembershipConfiguration getMembershipConfiguration() {
    return membershipConfiguration;
  }

  @Override
  public Long getGroupMembershipId() {
    return groupMembershipId;
  }

  @Override
  public Integer getPeriodsCompleted() {
    return periodsCompleted;
  }

  @Override
  public String toString() {
    return "GroupMemberState{"
        + "groupMemberStateLabel=" + groupMemberStateLabel + ", "
        + "membershipConfiguration=" + membershipConfiguration + ", "
        + "groupMembershipId=" + groupMembershipId + ", "
        + "periodsCompleted=" + periodsCompleted
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroupMemberState) {
      GroupMemberState that = (GroupMemberState) o;
      return (this.groupMemberStateLabel.equals(that.getGroupMemberStateLabel()))
           && (this.membershipConfiguration.equals(that.getMembershipConfiguration()))
           && (this.groupMembershipId.equals(that.getGroupMembershipId()))
           && (this.periodsCompleted.equals(that.getPeriodsCompleted()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= groupMemberStateLabel.hashCode();
    h *= 1000003;
    h ^= membershipConfiguration.hashCode();
    h *= 1000003;
    h ^= groupMembershipId.hashCode();
    h *= 1000003;
    h ^= periodsCompleted.hashCode();
    return h;
  }

  static final class Builder extends GroupMemberState.GroupMemberStateBuilder {
    private GroupMemberStrategyLabel groupMemberStateLabel;
    private MembershipConfiguration membershipConfiguration;
    private Long groupMembershipId;
    private Integer periodsCompleted;
    Builder() {
    }
    Builder(GroupMemberState source) {
      this.groupMemberStateLabel = source.getGroupMemberStateLabel();
      this.membershipConfiguration = source.getMembershipConfiguration();
      this.groupMembershipId = source.getGroupMembershipId();
      this.periodsCompleted = source.getPeriodsCompleted();
    }
    @Override
    public GroupMemberState.GroupMemberStateBuilder setGroupMemberStateLabel(GroupMemberStrategyLabel groupMemberStateLabel) {
      this.groupMemberStateLabel = groupMemberStateLabel;
      return this;
    }
    @Override
    public GroupMemberState.GroupMemberStateBuilder setMembershipConfiguration(MembershipConfiguration membershipConfiguration) {
      this.membershipConfiguration = membershipConfiguration;
      return this;
    }
    @Override
    public GroupMemberState.GroupMemberStateBuilder setGroupMembershipId(Long groupMembershipId) {
      this.groupMembershipId = groupMembershipId;
      return this;
    }
    @Override
    public GroupMemberState.GroupMemberStateBuilder setPeriodsCompleted(Integer periodsCompleted) {
      this.periodsCompleted = periodsCompleted;
      return this;
    }
    @Override
    public GroupMemberState build() {
      String missing = "";
      if (groupMemberStateLabel == null) {
        missing += " groupMemberStateLabel";
      }
      if (membershipConfiguration == null) {
        missing += " membershipConfiguration";
      }
      if (groupMembershipId == null) {
        missing += " groupMembershipId";
      }
      if (periodsCompleted == null) {
        missing += " periodsCompleted";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_GroupMemberState(
          this.groupMemberStateLabel,
          this.membershipConfiguration,
          this.groupMembershipId,
          this.periodsCompleted);
    }
  }
}
