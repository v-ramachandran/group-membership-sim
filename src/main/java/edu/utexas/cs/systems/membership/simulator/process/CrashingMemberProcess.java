package edu.utexas.cs.systems.membership.simulator.process;

import com.google.common.collect.Table;

import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.predicate.Predicate;

public interface CrashingMemberProcess extends Runnable {

    public void initiate();

    public void killProcess();

    public boolean isAlive();

    public void cleanUp();

    public void addSendMessageAbortCondition(
        final Predicate<Table<Integer, MessageType, Integer>> policy);

    public void updateNumberTimesToRun(int numberTimesToRun);
}
