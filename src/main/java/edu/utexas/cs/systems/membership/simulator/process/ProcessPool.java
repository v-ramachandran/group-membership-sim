package edu.utexas.cs.systems.membership.simulator.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStrategyLabel;
import edu.utexas.cs.systems.membership.simulator.member.MembershipConfiguration;

public class ProcessPool {

    private Map<Integer, CrashingMemberProcess> memberPool;
    private ProcessPoolFactory processPoolFactory;
    private boolean hasActiveProcesses;
    private MembershipConfiguration membershipConfiguration;
    private GroupMemberStrategyLabel groupMemberStrategyLabel;

    public ProcessPool(final ProcessPoolFactory processPoolFactory) {
        this.processPoolFactory = processPoolFactory;
        this.memberPool = new HashMap<Integer, CrashingMemberProcess>();
        this.hasActiveProcesses = false;

        this.membershipConfiguration = MembershipConfiguration.defaultConfiguration();
        this.groupMemberStrategyLabel = GroupMemberStrategyLabel.PERIODIC_BROADCAST;
    }

    public void initialize() throws Exception {
        final MembershipConfiguration newConfiguration = 
            MembershipConfiguration.builder(membershipConfiguration)
                .setInitialStartMillis(System.currentTimeMillis())
                .build();
        final GroupMemberState groupMemberState = GroupMemberState.builder()
            .setGroupMemberStateLabel(groupMemberStrategyLabel)
            .setMembershipConfiguration(newConfiguration)
            .setPeriodsCompleted(0)
            .build();
        memberPool = processPoolFactory.createMemberProcesses(groupMemberState);
    }

    public CrashingMemberProcess retrieveMemberProcess(final int memberId) {
        return memberPool.get(memberId);
    }

    public void cleanUp() {
        for (final Entry<Integer, CrashingMemberProcess> entry : memberPool.entrySet()) {
            final CrashingMemberProcess runnableProcess = entry.getValue();
            if (runnableProcess.isAlive()) {
                runnableProcess.cleanUp();
            }
        }
        this.hasActiveProcesses = false;
    }

    public void startAllProcesses(final int numberTimesToRun) {
        for (final Entry<Integer, CrashingMemberProcess> entry : memberPool.entrySet()) {
            entry.getValue().updateNumberTimesToRun(numberTimesToRun);
            new Thread(entry.getValue()).start();
        }
        this.hasActiveProcesses = true;
    }

    public boolean hasActiveProcesses() {
        return hasActiveProcesses;
    }

    public void reset() throws Exception {
        cleanUp();
    }

    public void setStrategy(final GroupMemberStrategyLabel groupMemberStrategyLabel) {
        this.groupMemberStrategyLabel = groupMemberStrategyLabel;
    }

    public GroupMemberStrategyLabel getStrategy() {
        return this.groupMemberStrategyLabel;
    }

    public MembershipConfiguration getMembershipConfiguration() {
        return membershipConfiguration;
    }

    public void setMembershipConfiguration(final MembershipConfiguration membershipConfiguration) {
        this.membershipConfiguration = membershipConfiguration;
    }
}
