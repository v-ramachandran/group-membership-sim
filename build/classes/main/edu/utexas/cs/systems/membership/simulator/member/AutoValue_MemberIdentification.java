
package edu.utexas.cs.systems.membership.simulator.member;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_MemberIdentification extends MemberIdentification {

  private final int id;
  private final String hostname;
  private final int port;

  private AutoValue_MemberIdentification(
      int id,
      String hostname,
      int port) {
    this.id = id;
    if (hostname == null) {
      throw new NullPointerException("Null hostname");
    }
    this.hostname = hostname;
    this.port = port;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public int getPort() {
    return port;
  }

  @Override
  public String toString() {
    return "MemberIdentification{"
        + "id=" + id + ", "
        + "hostname=" + hostname + ", "
        + "port=" + port
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MemberIdentification) {
      MemberIdentification that = (MemberIdentification) o;
      return (this.id == that.getId())
           && (this.hostname.equals(that.getHostname()))
           && (this.port == that.getPort());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= id;
    h *= 1000003;
    h ^= hostname.hashCode();
    h *= 1000003;
    h ^= port;
    return h;
  }

  static final class Builder extends MemberIdentification.MemberIdentificationBuilder {
    private Integer id;
    private String hostname;
    private Integer port;
    Builder() {
    }
    Builder(MemberIdentification source) {
      this.id = source.getId();
      this.hostname = source.getHostname();
      this.port = source.getPort();
    }
    @Override
    public MemberIdentification.MemberIdentificationBuilder setId(int id) {
      this.id = id;
      return this;
    }
    @Override
    public MemberIdentification.MemberIdentificationBuilder setHostname(String hostname) {
      this.hostname = hostname;
      return this;
    }
    @Override
    public MemberIdentification.MemberIdentificationBuilder setPort(int port) {
      this.port = port;
      return this;
    }
    @Override
    public MemberIdentification build() {
      String missing = "";
      if (id == null) {
        missing += " id";
      }
      if (hostname == null) {
        missing += " hostname";
      }
      if (port == null) {
        missing += " port";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_MemberIdentification(
          this.id,
          this.hostname,
          this.port);
    }
  }
}
