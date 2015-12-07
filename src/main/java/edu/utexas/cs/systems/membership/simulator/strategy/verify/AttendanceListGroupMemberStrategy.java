package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.MembershipConfiguration;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.NewGroupMessage;

public class AttendanceListGroupMemberStrategy implements MembershipStrategy {

    private AbortingNetworkCommunicator networkCommunicator;

    public AttendanceListGroupMemberStrategy(final AbortingNetworkCommunicator networkCommunicator) {
        this.networkCommunicator = networkCommunicator;
    }

    @Override
    public GroupMemberState execute(GroupMemberState currentState) {
        
        return null;
    }

    @Override
    public GroupMemberState initiate(final GroupMemberState initialState) {

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
