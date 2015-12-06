package edu.utexas.cs.systems.membership.simulator.strategy.join;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStateLabel;
import edu.utexas.cs.systems.membership.simulator.network.NetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessage;

public class DefaultJoinStrategy implements JoinStrategy {

    private NetworkCommunicator networkCommunicator;

    @Override
    public GroupMemberState startNewGroup(GroupMemberState groupMemberState) {
        return null;
    }

    @Override
    public GroupMemberState execute(final GroupMemberState currentState) {
        
        // If original sender
        //   send newgroup message
        // else
        //   handle network message
        //      new group:
        //        broadcast present
        //      present:
        //        update state
        
        return null;
    }
    
    private NetworkMessage collectLatestNewGroupMessage() {
        return null;
    }
}
