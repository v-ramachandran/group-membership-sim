package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.member.OrderedMemberList;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.message.MemberDroppedMessage;

public class PeriodicBroadcastMembershipCheck implements Runnable {
    private AbortingNetworkCommunicator networkCommunicator;
    private OrderedMemberList orderedMemberList;

    public PeriodicBroadcastMembershipCheck(final AbortingNetworkCommunicator networkCommunicator,
        final OrderedMemberList orderedMemberList) {

        this.networkCommunicator = networkCommunicator;
        this.orderedMemberList = orderedMemberList;
    }

    @Override
    public void run() {
        if (orderedMemberList.hasAbsentMembers()) {
            final MemberDroppedMessage memberDroppedMessage = MemberDroppedMessage.builder().build();
            networkCommunicator.unicastToSelf(memberDroppedMessage);
        } else {
            orderedMemberList.resetAttendance();
        }
    }
}