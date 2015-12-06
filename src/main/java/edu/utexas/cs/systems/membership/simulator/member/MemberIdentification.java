package edu.utexas.cs.systems.membership.simulator.member;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MemberIdentification implements Comparable<MemberIdentification> {

    public abstract long getId();
    public abstract String getHostname();
    public abstract int getPort();

    @AutoValue.Builder
    protected abstract static class MemberIdentificationBuilder {
        public abstract MemberIdentificationBuilder setId(final long id);
        public abstract MemberIdentificationBuilder setHostname(final String hostname);
        public abstract MemberIdentificationBuilder setPort(final int port);

        public abstract MemberIdentification build();
    }

    public static MemberIdentificationBuilder builder() {
        return new AutoValue_MemberIdentification.Builder();
    }

    @Override
    public int compareTo(final MemberIdentification memberIdentification) {
        if (memberIdentification.getId() > this.getId()) {
            return -1;
        } else if (memberIdentification.getId() < this.getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}
