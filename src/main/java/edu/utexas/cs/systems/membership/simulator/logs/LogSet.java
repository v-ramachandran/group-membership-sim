package edu.utexas.cs.systems.membership.simulator.logs;

import java.util.TreeSet;

public class LogSet {

    private TreeSet<LogItem> logItems;
    private static final Object LOCK = new Object();

    public LogSet() {
        this.logItems = new TreeSet<LogItem>();
    }

    public void addLogItem(final LogItem logItem) {
        synchronized(LOCK) {
            logItems.add(logItem);
        }
    }

    private void printGroupHeader(final LogItem logItem) {
        System.out.println(String.format("================== GROUP ID: %s", logItem.getGroupId()));
    }

    private void printPeriodHeader(final LogItem logItem) {
        System.out.println(String.format("========= PERIOD: %s + %s*PI", 
            logItem.getGroupId(), logItem.getPeriod()));
    }

    public void prettyPrintToConsole() {
        synchronized(LOCK) {
            Long groupId = -1L;
            Integer period = -1;
            for (LogItem logItem : logItems) {
                if (logItem.getGroupId() > groupId) {
                    groupId = logItem.getGroupId();
                    printGroupHeader(logItem);
                }

                if (period < logItem.getPeriod()) {
                    period = logItem.getPeriod();
                    printPeriodHeader(logItem);
                }

                System.out.println(String.format("-- At %s Member %s %s", logItem.getEventTimestamp(),
                    logItem.getProcessId(), logItem.getEventDescription()));
            }
        }
    }

    public void clear() {
        synchronized(LOCK) {
            logItems.clear();
        }
    }
}
