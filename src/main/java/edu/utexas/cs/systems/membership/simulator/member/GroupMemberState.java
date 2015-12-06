package edu.utexas.cs.systems.membership.simulator.member;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GroupMemberState {

    public abstract GroupMemberStateLabel getGroupMemberStateLabel();

    @AutoValue.Builder
    protected static abstract class GroupMemberStateBuilder {
        public abstract GroupMemberStateBuilder setGroupMemberStateLabel(
            final GroupMemberStateLabel groupMemberStateLabel);
        public abstract GroupMemberState build();
    }

    public static GroupMemberStateBuilder builder() {
        return new AutoValue_GroupMemberState.Builder();
    }
}
