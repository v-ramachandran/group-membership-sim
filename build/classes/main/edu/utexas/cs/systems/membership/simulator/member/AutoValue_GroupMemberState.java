
package edu.utexas.cs.systems.membership.simulator.member;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroupMemberState extends GroupMemberState {

  private final GroupMemberStateLabel groupMemberStateLabel;

  private AutoValue_GroupMemberState(
      GroupMemberStateLabel groupMemberStateLabel) {
    if (groupMemberStateLabel == null) {
      throw new NullPointerException("Null groupMemberStateLabel");
    }
    this.groupMemberStateLabel = groupMemberStateLabel;
  }

  @Override
  public GroupMemberStateLabel getGroupMemberStateLabel() {
    return groupMemberStateLabel;
  }

  @Override
  public String toString() {
    return "GroupMemberState{"
        + "groupMemberStateLabel=" + groupMemberStateLabel
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroupMemberState) {
      GroupMemberState that = (GroupMemberState) o;
      return (this.groupMemberStateLabel.equals(that.getGroupMemberStateLabel()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= groupMemberStateLabel.hashCode();
    return h;
  }

  static final class Builder extends GroupMemberState.GroupMemberStateBuilder {
    private GroupMemberStateLabel groupMemberStateLabel;
    Builder() {
    }
    Builder(GroupMemberState source) {
      this.groupMemberStateLabel = source.getGroupMemberStateLabel();
    }
    @Override
    public GroupMemberState.GroupMemberStateBuilder setGroupMemberStateLabel(GroupMemberStateLabel groupMemberStateLabel) {
      this.groupMemberStateLabel = groupMemberStateLabel;
      return this;
    }
    @Override
    public GroupMemberState build() {
      String missing = "";
      if (groupMemberStateLabel == null) {
        missing += " groupMemberStateLabel";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_GroupMemberState(
          this.groupMemberStateLabel);
    }
  }
}
