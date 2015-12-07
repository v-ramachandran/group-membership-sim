package edu.utexas.cs.systems.membership.simulator.command;

public class InternalErrorException extends RuntimeException {
    
    public InternalErrorException(final Throwable throwable) {
        super(throwable);
    }
}
