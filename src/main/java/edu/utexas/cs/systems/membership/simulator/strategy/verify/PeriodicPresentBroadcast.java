package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.logs.LogItem;
import edu.utexas.cs.systems.membership.simulator.logs.LogSet;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.ScriptedNetworkCrashException;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.message.PresentMessage;

public class PeriodicPresentBroadcast implements Runnable {
    private AbortingNetworkCommunicator networkCommunicator;
    private LogSet logSet;
    private int period;
    private long groupId;
    
    public PeriodicPresentBroadcast(final AbortingNetworkCommunicator networkCommunicator,
        final LogSet logSet, final long groupId) {

        this.networkCommunicator = networkCommunicator;
        this.logSet = logSet;
        this.period = 0;
        this.groupId = groupId;
    }

    @Override
    public void run() {
        period++;
        try {
            final PresentMessage presentMessage = PresentMessage.builder().build();
            networkCommunicator.broadcastMessage(presentMessage);
        } catch(ScriptedNetworkCrashException exception) {
            final long currentTime = System.currentTimeMillis();
            System.out.println(String.format("%s performed a scripted crash at %s.", 
                networkCommunicator.getSelf(), currentTime));
            LogItem crashLog = LogItem.builder()
                .crashedAfterSending(MessageType.PRESENT, currentTime, exception.getDestination())
                .setEventTimestamp(currentTime)
                .setProcessId(networkCommunicator.getSelf().getId())
                .setGroupId(groupId)
                .build();
            logSet.addLogItem(crashLog);
            networkCommunicator.shutdown();
            throw exception;
        }
    }

    public void updateGroupId(final long groupId) {
        this.groupId = groupId;
    }
}
