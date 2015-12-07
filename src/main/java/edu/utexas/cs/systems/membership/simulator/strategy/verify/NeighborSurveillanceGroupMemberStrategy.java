package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.MembershipConfiguration;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.NetworkMessage;
import edu.utexas.cs.systems.membership.simulator.network.message.NewGroupMessage;

public class NeighborSurveillanceGroupMemberStrategy implements MembershipStrategy {

    private AbortingNetworkCommunicator networkCommunicator;

    @Override
    public GroupMemberState execute(GroupMemberState currentState) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupMemberState initiate(GroupMemberState initialState) {

        final MembershipConfiguration membershipConfiguration = 
            initialState.getMembershipConfiguration();
        final long groupId = membershipConfiguration.getInitialStartMillis() 
            + membershipConfiguration.getBroadcastLatencyMillis();
        final NewGroupMessage newGroupMessage = NewGroupMessage.builder()
            .setTimestamp(groupId)
            .build();
        networkCommunicator.broadcastMessage(newGroupMessage);

        return GroupMemberState.builder(initialState)
            .build();
    }

    @Override
    public void shutdown() {
        
    }
}
