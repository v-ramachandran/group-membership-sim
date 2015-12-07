package edu.utexas.cs.systems.membership.simulator.member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

public class CircularOrderedMemberList implements OrderedMemberList {

    private TreeSet<MemberIdentification> memberIdSet;
    private Map<Integer, MemberIdentification> memberIdMap;
    private Set<Integer> presenceSet;
    
    protected CircularOrderedMemberList(final TreeSet<MemberIdentification> memberIdSet,
        final Map<Integer, MemberIdentification> memberIdMap, final Set<Integer> presenceSet) {

        this.memberIdMap = memberIdMap;
        this.memberIdSet = memberIdSet;
        this.presenceSet = presenceSet;
    }

    public static class Builder {
        private TreeSet<MemberIdentification> memberIdSet;
        private Map<Integer, MemberIdentification> memberIdMap;

        public Builder() {
            this.memberIdSet = new TreeSet<MemberIdentification>();
            this.memberIdMap = new HashMap<Integer, MemberIdentification>();
        }

        public Builder addMembers(final Iterable<MemberIdentification> memberIds) {
            for(MemberIdentification memberId : memberIds) {
                addMember(memberId);
            }
            
            return this;
        }

        public Builder addMember(final MemberIdentification memberIdentification) {
            memberIdSet.add(memberIdentification);
            memberIdMap.put(memberIdentification.getId(), memberIdentification);
            return this;
        }

        public CircularOrderedMemberList build() {
            return new CircularOrderedMemberList(memberIdSet, memberIdMap, new HashSet<Integer>());
        }
    }

    @Override
    public MemberIdentification getNextMember(final int memberId) {

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
    public MemberIdentification getPreviousMember(final int memberId) {

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
    public MemberIdentification getMember(final int memberId) {
        return memberIdMap.get(memberId);
    }

    @Override
    public void markAsPresent(final int memberId) {
        presenceSet.add(memberId);
    }

    @Override
    public boolean hasAbsentMembers() {
        return !Sets.difference(memberIdMap.keySet(), presenceSet).isEmpty();
    }

    @Override
    public Iterable<Integer> removeAbsentMembers() {
        final Iterable<Integer> absentMembers = Sets.difference(memberIdMap.keySet(), presenceSet);
        for (Integer memberId : absentMembers) {
            final MemberIdentification memberIdentification = memberIdMap.get(memberId);
            memberIdSet.remove(memberIdentification);
        }
        return absentMembers;
    }

    @Override
    public void resetAttendance() {
        presenceSet.clear();
    }
}
