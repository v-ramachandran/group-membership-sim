package edu.utexas.cs.systems.membership.simulator.network.message;

public class UnableToFindMessageTypeException extends RuntimeException {

    public UnableToFindMessageTypeException(final String message) {
        super(message);
    }
}
