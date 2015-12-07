
package edu.utexas.cs.systems.membership.simulator.network.message;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_MemberDroppedMessage extends MemberDroppedMessage {

  private AutoValue_MemberDroppedMessage(
 ) {
  }

  @Override
  public String toString() {
    return "MemberDroppedMessage{"
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MemberDroppedMessage) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    return h;
  }

  static final class Builder extends MemberDroppedMessage.MemberDroppedMessageBuilder {
    Builder() {
    }
    Builder(MemberDroppedMessage source) {
    }
    @Override
    public MemberDroppedMessage build() {
      return new AutoValue_MemberDroppedMessage(
   );
    }
  }
}
