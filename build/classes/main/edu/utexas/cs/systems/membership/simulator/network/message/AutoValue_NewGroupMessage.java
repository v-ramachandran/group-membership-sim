
package edu.utexas.cs.systems.membership.simulator.network.message;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_NewGroupMessage extends NewGroupMessage {

  private final long timestamp;

  private AutoValue_NewGroupMessage(
      long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "NewGroupMessage{"
        + "timestamp=" + timestamp
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof NewGroupMessage) {
      NewGroupMessage that = (NewGroupMessage) o;
      return (this.timestamp == that.getTimestamp());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= (timestamp >>> 32) ^ timestamp;
    return h;
  }

  static final class Builder extends NewGroupMessage.NewGroupMessageBuilder {
    private Long timestamp;
    Builder() {
    }
    Builder(NewGroupMessage source) {
      this.timestamp = source.getTimestamp();
    }
    @Override
    public NewGroupMessage.NewGroupMessageBuilder setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }
    @Override
    public NewGroupMessage build() {
      String missing = "";
      if (timestamp == null) {
        missing += " timestamp";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_NewGroupMessage(
          this.timestamp);
    }
  }
}
