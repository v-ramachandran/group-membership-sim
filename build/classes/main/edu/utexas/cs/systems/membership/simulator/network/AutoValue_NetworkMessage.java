
package edu.utexas.cs.systems.membership.simulator.network;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_NetworkMessage extends NetworkMessage {

  private final MemberIdentification sender;
  private final MemberIdentification recipient;
  private final String messagePayload;
  private final MessageType messageType;

  private AutoValue_NetworkMessage(
      MemberIdentification sender,
      MemberIdentification recipient,
      String messagePayload,
      MessageType messageType) {
    if (sender == null) {
      throw new NullPointerException("Null sender");
    }
    this.sender = sender;
    if (recipient == null) {
      throw new NullPointerException("Null recipient");
    }
    this.recipient = recipient;
    if (messagePayload == null) {
      throw new NullPointerException("Null messagePayload");
    }
    this.messagePayload = messagePayload;
    if (messageType == null) {
      throw new NullPointerException("Null messageType");
    }
    this.messageType = messageType;
  }

  @Override
  public MemberIdentification getSender() {
    return sender;
  }

  @Override
  public MemberIdentification getRecipient() {
    return recipient;
  }

  @Override
  public String getMessagePayload() {
    return messagePayload;
  }

  @Override
  public MessageType getMessageType() {
    return messageType;
  }

  @Override
  public String toString() {
    return "NetworkMessage{"
        + "sender=" + sender + ", "
        + "recipient=" + recipient + ", "
        + "messagePayload=" + messagePayload + ", "
        + "messageType=" + messageType
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof NetworkMessage) {
      NetworkMessage that = (NetworkMessage) o;
      return (this.sender.equals(that.getSender()))
           && (this.recipient.equals(that.getRecipient()))
           && (this.messagePayload.equals(that.getMessagePayload()))
           && (this.messageType.equals(that.getMessageType()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= sender.hashCode();
    h *= 1000003;
    h ^= recipient.hashCode();
    h *= 1000003;
    h ^= messagePayload.hashCode();
    h *= 1000003;
    h ^= messageType.hashCode();
    return h;
  }

  static final class Builder extends NetworkMessage.NetworkMessageBuilder {
    private MemberIdentification sender;
    private MemberIdentification recipient;
    private String messagePayload;
    private MessageType messageType;
    Builder() {
    }
    Builder(NetworkMessage source) {
      this.sender = source.getSender();
      this.recipient = source.getRecipient();
      this.messagePayload = source.getMessagePayload();
      this.messageType = source.getMessageType();
    }
    @Override
    public NetworkMessage.NetworkMessageBuilder setSender(MemberIdentification sender) {
      this.sender = sender;
      return this;
    }
    @Override
    public NetworkMessage.NetworkMessageBuilder setRecipient(MemberIdentification recipient) {
      this.recipient = recipient;
      return this;
    }
    @Override
    public NetworkMessage.NetworkMessageBuilder setMessagePayload(String messagePayload) {
      this.messagePayload = messagePayload;
      return this;
    }
    @Override
    public NetworkMessage.NetworkMessageBuilder setMessageType(MessageType messageType) {
      this.messageType = messageType;
      return this;
    }
    @Override
    public NetworkMessage build() {
      String missing = "";
      if (sender == null) {
        missing += " sender";
      }
      if (recipient == null) {
        missing += " recipient";
      }
      if (messagePayload == null) {
        missing += " messagePayload";
      }
      if (messageType == null) {
        missing += " messageType";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_NetworkMessage(
          this.sender,
          this.recipient,
          this.messagePayload,
          this.messageType);
    }
  }
}
