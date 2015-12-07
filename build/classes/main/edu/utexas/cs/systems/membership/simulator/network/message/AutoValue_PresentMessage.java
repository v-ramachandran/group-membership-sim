
package edu.utexas.cs.systems.membership.simulator.network.message;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PresentMessage extends PresentMessage {

  private final long timestamp;

  private AutoValue_PresentMessage(
      long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "PresentMessage{"
        + "timestamp=" + timestamp
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PresentMessage) {
      PresentMessage that = (PresentMessage) o;
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

  static final class Builder extends PresentMessage.PresentMessageBuilder {
    private Long timestamp;
    Builder() {
    }
    Builder(PresentMessage source) {
      this.timestamp = source.getTimestamp();
    }
    @Override
    public PresentMessage.PresentMessageBuilder setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }
    @Override
    public PresentMessage build() {
      String missing = "";
      if (timestamp == null) {
        missing += " timestamp";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_PresentMessage(
          this.timestamp);
    }
  }
}
