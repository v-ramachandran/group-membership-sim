package edu.utexas.cs.systems.membership.simulator.network.predicate;

public interface Predicate<DataType> {
    
    /**
     * @return true if the input data satisfies the binary condition
     * represented by this {@link Predicate}.
     */
    public boolean isSatisfied(final DataType data);
}
