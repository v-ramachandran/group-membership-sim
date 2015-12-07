package edu.utexas.cs.systems.membership.simulator.member;

import java.util.Set;

public interface OrderedMemberList {

    public MemberIdentification getMember(final int memberId);

    public MemberIdentification getNextMember(final int memberId);

    public MemberIdentification getPreviousMember(final int memberId);

    public Iterable<MemberIdentification> getAllMembers();

    public void markAsPresent(final int memberId);

    public boolean hasAbsentMembers();

    public void removeAbsentMembers();

    public void resetAttendance();
}
