package edu.utexas.cs.systems.membership.simulator.strategy.join;

import edu.utexas.cs.systems.membership.simulator.member.ExecutableStateLogic;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessage;

public interface JoinStrategy extends ExecutableStateLogic {

    public GroupMemberState startNewGroup(final GroupMemberState groupMemberState);
}
