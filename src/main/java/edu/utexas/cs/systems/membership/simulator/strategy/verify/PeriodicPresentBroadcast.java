package edu.utexas.cs.systems.membership.simulator.strategy.verify;

import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.ScriptedNetworkCrashException;
import edu.utexas.cs.systems.membership.simulator.network.message.PresentMessage;

public class PeriodicPresentBroadcast implements Runnable {
    private AbortingNetworkCommunicator networkCommunicator;

    public PeriodicPresentBroadcast(final AbortingNetworkCommunicator networkCommunicator) {
        this.networkCommunicator = networkCommunicator;
    }

    @Override
    public void run() {
        try {
            final PresentMessage presentMessage = PresentMessage.builder().build();
            networkCommunicator.broadcastMessage(presentMessage);
        } catch(ScriptedNetworkCrashException exception) {
            System.out.println(String.format("%s performed a scripted crash at %s.", 
                networkCommunicator.getSelf(), System.currentTimeMillis()));
            throw exception;
        }
    }
}
