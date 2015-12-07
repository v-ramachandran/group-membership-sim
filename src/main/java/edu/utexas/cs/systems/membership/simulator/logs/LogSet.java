package edu.utexas.cs.systems.membership.simulator.logs;

import java.util.TreeSet;

public class LogSet {

    private TreeSet<LogItem> logItems;
    private static final Object LOCK = new Object();

    public void addLogItem(final LogItem logItem) {
        synchronized(LOCK) {
            logItems.add(logItem);
        }
    }

    public void prettyPrintToConsole() {
        synchronized(LOCK) {
            for (LogItem logItem : logItems) {
                
            }
        }
    }

    public void clear() {
        synchronized(LOCK) {
            logItems.clear();
        }
    }
}
