
package edu.utexas.cs.systems.membership.simulator.logs;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LogItem extends LogItem {

  private final Long groupId;
  private final Integer period;
  private final Integer processId;
  private final Long eventTimestamp;
  private final String eventDescription;

  private AutoValue_LogItem(
      Long groupId,
      Integer period,
      Integer processId,
      Long eventTimestamp,
      String eventDescription) {
    if (groupId == null) {
      throw new NullPointerException("Null groupId");
    }
    this.groupId = groupId;
    if (period == null) {
      throw new NullPointerException("Null period");
    }
    this.period = period;
    if (processId == null) {
      throw new NullPointerException("Null processId");
    }
    this.processId = processId;
    if (eventTimestamp == null) {
      throw new NullPointerException("Null eventTimestamp");
    }
    this.eventTimestamp = eventTimestamp;
    if (eventDescription == null) {
      throw new NullPointerException("Null eventDescription");
    }
    this.eventDescription = eventDescription;
  }

  @Override
  public Long getGroupId() {
    return groupId;
  }

  @Override
  public Integer getPeriod() {
    return period;
  }

  @Override
  public Integer getProcessId() {
    return processId;
  }

  @Override
  public Long getEventTimestamp() {
    return eventTimestamp;
  }

  @Override
  public String getEventDescription() {
    return eventDescription;
  }

  @Override
  public String toString() {
    return "LogItem{"
        + "groupId=" + groupId + ", "
        + "period=" + period + ", "
        + "processId=" + processId + ", "
        + "eventTimestamp=" + eventTimestamp + ", "
        + "eventDescription=" + eventDescription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LogItem) {
      LogItem that = (LogItem) o;
      return (this.groupId.equals(that.getGroupId()))
           && (this.period.equals(that.getPeriod()))
           && (this.processId.equals(that.getProcessId()))
           && (this.eventTimestamp.equals(that.getEventTimestamp()))
           && (this.eventDescription.equals(that.getEventDescription()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= groupId.hashCode();
    h *= 1000003;
    h ^= period.hashCode();
    h *= 1000003;
    h ^= processId.hashCode();
    h *= 1000003;
    h ^= eventTimestamp.hashCode();
    h *= 1000003;
    h ^= eventDescription.hashCode();
    return h;
  }

  static final class Builder extends LogItem.LogItemBuilder {
    private Long groupId;
    private Integer period;
    private Integer processId;
    private Long eventTimestamp;
    private String eventDescription;
    Builder() {
    }
    Builder(LogItem source) {
      this.groupId = source.getGroupId();
      this.period = source.getPeriod();
      this.processId = source.getProcessId();
      this.eventTimestamp = source.getEventTimestamp();
      this.eventDescription = source.getEventDescription();
    }
    @Override
    public LogItem.LogItemBuilder setGroupId(Long groupId) {
      this.groupId = groupId;
      return this;
    }
    @Override
    public LogItem.LogItemBuilder setPeriod(Integer period) {
      this.period = period;
      return this;
    }
    @Override
    public LogItem.LogItemBuilder setProcessId(Integer processId) {
      this.processId = processId;
      return this;
    }
    @Override
    public LogItem.LogItemBuilder setEventTimestamp(Long eventTimestamp) {
      this.eventTimestamp = eventTimestamp;
      return this;
    }
    @Override
    public LogItem.LogItemBuilder setEventDescription(String eventDescription) {
      this.eventDescription = eventDescription;
      return this;
    }
    @Override
    public LogItem build() {
      String missing = "";
      if (groupId == null) {
        missing += " groupId";
      }
      if (period == null) {
        missing += " period";
      }
      if (processId == null) {
        missing += " processId";
      }
      if (eventTimestamp == null) {
        missing += " eventTimestamp";
      }
      if (eventDescription == null) {
        missing += " eventDescription";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_LogItem(
          this.groupId,
          this.period,
          this.processId,
          this.eventTimestamp,
          this.eventDescription);
    }
  }
}
