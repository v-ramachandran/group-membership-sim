package edu.utexas.cs.systems.membership.simulator.network;

public class ScriptedNetworkCrashException extends RuntimeException {
    
    private Integer destination;
    
    public ScriptedNetworkCrashException(final Integer destination) {
        super();
        this.destination = destination;
    }

    public Integer getDestination() {
        return destination;
    }
}
