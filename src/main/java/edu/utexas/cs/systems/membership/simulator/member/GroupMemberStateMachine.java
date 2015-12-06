package edu.utexas.cs.systems.membership.simulator.member;

import edu.utexas.cs.systems.membership.simulator.strategy.join.JoinStrategy;
import edu.utexas.cs.systems.membership.simulator.strategy.verify.MembershipVerificationStrategy;

public class GroupMemberStateMachine {

    private JoinStrategy joinStrategy;
    private MembershipVerificationStrategy membershipVerificationStrategy;
    private GroupMemberState groupMemberState;

    public GroupMemberStateMachine(final JoinStrategy joinStrategy, 
        final MembershipVerificationStrategy membershipVerificationStrategy) {

        this.joinStrategy = joinStrategy;
        this.membershipVerificationStrategy = membershipVerificationStrategy;
    }

    /**
     * 
     */
    public void execute() {
        if (groupMemberState.equals(GroupMemberStateLabel.IN_VERIFICATION)) {
            
        } else if (groupMemberState.equals(GroupMemberStateLabel.TO_JOIN_GROUP)) {
            joinStrategy.execute(groupMemberState);
        } else if (groupMemberState.equals(GroupMemberStateLabel.TO_START_GROUP)) {
            joinStrategy.startNewGroup(groupMemberState);
        }
    }
}
