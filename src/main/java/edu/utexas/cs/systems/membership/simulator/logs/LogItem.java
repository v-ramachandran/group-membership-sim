package edu.utexas.cs.systems.membership.simulator.logs;

import com.google.auto.value.AutoValue;

import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;

@AutoValue
public abstract class LogItem implements Comparable<LogItem>{

    public abstract Long getGroupId();
    public abstract Integer getPeriod();
    public abstract Integer getProcessId();
    public abstract Long getEventTimestamp();
    public abstract String getEventDescription();

    @AutoValue.Builder
    public abstract class LogItemBuilder {
        public abstract LogItemBuilder setGroupId(final Long groupId);
        public abstract LogItemBuilder setProcessId(final Long processId);
        public abstract LogItemBuilder setPeriod(final Integer period);
        public abstract LogItemBuilder setEventTimestamp(final Long eventTimestamp);
        public abstract LogItemBuilder setEventDescription(final String eventDescription);
        public abstract LogItem build();

        public LogItemBuilder didReceive(final MessageType messageType, final Iterable<Integer> from) {
            String template = String.format("- received %s from:[ ", messageType);
            for(Integer integer : from) {
                template += integer.toString();
                template += " ";
            }
            template += "]";
            setEventDescription(template);
            return this;
        }

        public LogItemBuilder didNotReceive(final MessageType messageType, final Iterable<Integer> from) {
            String template = String.format("- did not receive %s from:[ ", messageType);
            for(Integer integer : from) {
                template += integer.toString();
                template += " ";
            }
            template += "]";
            setEventDescription(template);
            return this;
        }

        public LogItemBuilder crashedAfterSending(final MessageType messageType, 
            final Long crashTime, final Integer to) {

            String template = String.format("- crashed at %s after sending %s to: [ %s ]", crashTime, messageType, to);
            setEventDescription(template);
            return this;
        }

        public LogItemBuilder formedANewGroup(final Long groupId, final Iterable<Integer> with) {
            String template = String.format("- formed a new group at %s with [", groupId);
            for(Integer integer : with) {
                template += integer.toString();
                template += " ";
            }
            template += "]";
            setEventDescription(template);
            return this;
        }
    }

    public static LogItemBuilder builder() {
        return new AutoValue_LogItem.Builder();
    }

    @Override
    public int compareTo(LogItem comparison) {
        if (getGroupId() < comparison.getGroupId()) {
            return -1;
        } else if(getGroupId() > comparison.getGroupId()) {
            return 1;
        } else {
            if (getProcessId() < comparison.getProcessId()) {
                return -1;
            } else if(getProcessId() > comparison.getProcessId()) {
                return 1;
            } else {
                if (getEventTimestamp() < comparison.getEventTimestamp()) {
                    return -1;
                } else if (getEventTimestamp() > comparison.getEventTimestamp()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
