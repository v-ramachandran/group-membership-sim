package edu.utexas.cs.systems.membership.simulator.member;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CircularOrderedMemberList implements OrderedMemberList {

    private TreeSet<MemberIdentification> memberIdSet;
    private Map<Long, MemberIdentification> memberIdMap;

    protected CircularOrderedMemberList(final TreeSet<MemberIdentification> memberIdSet,
        final Map<Long, MemberIdentification> memberIdMap) {

        this.memberIdMap = memberIdMap;
        this.memberIdSet = memberIdSet;
    }

    public class Builder {
        private TreeSet<MemberIdentification> memberIdSet;
        private Map<Long, MemberIdentification> memberIdMap;

        public Builder() {
            this.memberIdSet = new TreeSet<MemberIdentification>();
            this.memberIdMap = new HashMap<Long, MemberIdentification>();
        }

        public void addMember(final MemberIdentification memberIdentification) {
            memberIdSet.add(memberIdentification);
            memberIdMap.put(memberIdentification.getId(), memberIdentification);
        }

        public CircularOrderedMemberList build() {
            return new CircularOrderedMemberList(memberIdSet, memberIdMap);
        }
    }

    @Override
    public MemberIdentification getNextMember(final long memberId) {

        if (!memberIdMap.containsKey(memberId)) {
            return null;
        }

        final MemberIdentification memberIdentification = memberIdMap.get(memberId);
        final MemberIdentification nextMemberIdentification = 
            memberIdSet.higher(memberIdentification);

        if (nextMemberIdentification != null) {
            return nextMemberIdentification;
        } else {
            return memberIdSet.first();
        }
    }

    @Override
    public MemberIdentification getPreviousMember(final long memberId) {

        if (!memberIdMap.containsKey(memberId)) {
            return null;
        }

        final MemberIdentification memberIdentification = memberIdMap.get(memberId);
        final MemberIdentification previousMemberIdentification = 
            memberIdSet.higher(memberIdentification);

        if (previousMemberIdentification != null) {
            return previousMemberIdentification;
        } else {
            return memberIdSet.last();
        }
    }

    @Override
    public Iterable<MemberIdentification> getAllMembers() {
        return memberIdSet;
    }

    @Override
    public MemberIdentification getMember(long memberId) {
        return null;
    }

    @Override
    public boolean isPresent(long memberId) {
        return false;
    }

    @Override
    public boolean markAsPresent(long memberId) {
        return false;
    }
}
