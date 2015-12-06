package edu.utexas.cs.systems.membership.simulator.member;

public interface OrderedMemberList {

    /**
     * 
     */
    public MemberIdentification getMember(final long memberId);

    /**
     * 
     */
    public MemberIdentification getNextMember(final long memberId);

    /**
     * 
     */
    public MemberIdentification getPreviousMember(final long memberId);

    /**
     * 
     */
    public Iterable<MemberIdentification> getAllMembers();

    /**
     * 
     */
    public boolean isPresent(final long memberId);

    /**
     * 
     */
    public boolean markAsPresent(final long memberId);
}
