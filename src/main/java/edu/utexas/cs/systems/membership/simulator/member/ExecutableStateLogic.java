package edu.utexas.cs.systems.membership.simulator.member;

public interface ExecutableStateLogic {

    /**
     * 
     */
    public GroupMemberState execute(final GroupMemberState currentState);

    public void shutdown();
}
