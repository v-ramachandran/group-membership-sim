package edu.utexas.cs.systems.membership.simulator.network;


public class SimplePortValueGenerator implements PortValueGenerator {
    
    private final int memberValueBase;
    
    public SimplePortValueGenerator(final int memberValueBase) {
        this.memberValueBase = memberValueBase;
    }

    @Override
    public int generatePort(int memberId) {
        return memberValueBase + memberId;
    }
}
