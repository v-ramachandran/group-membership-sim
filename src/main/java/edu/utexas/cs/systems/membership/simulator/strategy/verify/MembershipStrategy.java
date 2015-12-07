package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.member.ExecutableStateLogic;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;

public interface MembershipStrategy extends ExecutableStateLogic {

    public GroupMemberState initiate(final GroupMemberState initialState);
    public GroupMemberState execute(final GroupMemberState currentState);
}
