package edu.utexas.cs.systems.membership.simulator.member;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GroupMemberState {

    public abstract GroupMemberStrategyLabel getGroupMemberStateLabel();
    public abstract MembershipConfiguration getMembershipConfiguration();
    public abstract Long getGroupMembershipId();
    public abstract Integer getPeriodsCompleted();
    
    @AutoValue.Builder
    public static abstract class GroupMemberStateBuilder {
        public abstract GroupMemberStateBuilder setGroupMemberStateLabel(
            final GroupMemberStrategyLabel groupMemberStateLabel);
        public abstract GroupMemberStateBuilder setMembershipConfiguration(
            final MembershipConfiguration membershipConfiguration);
        public abstract GroupMemberStateBuilder setGroupMembershipId(
            final Long groupMembershipId);
        public abstract GroupMemberStateBuilder setPeriodsCompleted(
            final Integer periodsCompleted);
        public abstract GroupMemberState build();
    }

    public static GroupMemberStateBuilder builder() {
        return new AutoValue_GroupMemberState.Builder()
            .setGroupMembershipId(0L)
            .setPeriodsCompleted(0)
            .setGroupMemberStateLabel(GroupMemberStrategyLabel.PERIODIC_BROADCAST)
            .setMembershipConfiguration(MembershipConfiguration.defaultConfiguration());
    }

    public static GroupMemberStateBuilder builder(final GroupMemberState groupMemberState) {
        return new AutoValue_GroupMemberState.Builder()
            .setGroupMemberStateLabel(groupMemberState.getGroupMemberStateLabel())
            .setMembershipConfiguration(groupMemberState.getMembershipConfiguration())
            .setGroupMembershipId(groupMemberState.getGroupMembershipId())
            .setPeriodsCompleted(groupMemberState.getPeriodsCompleted());
    }
}
