package edu.utexas.cs.systems.membership.simulator.network.message;

public enum MessageType {

    NEW_GROUP(AutoValue_NewGroupMessage.class), PRESENT(AutoValue_PresentMessage.class), // Common Message Types
    MEMBER_DROPPED(AutoValue_MemberDroppedMessage.class), // Augmentation to Periodic Broadcast to handle Membership Checking
    LIST(ListMessage.class); // Attendance List Protocol required

    private Class<? extends Object> associatedClass;

    private MessageType(final Class<? extends Object> associatedClass) {
        this.associatedClass = associatedClass;
    }

    @SuppressWarnings("unchecked")
    public <Message> Class<Message> getAssociatedClass() {
        return (Class<Message>) associatedClass;
    }

    public static <Message> MessageType retrieveMatchingType(
            final Class<Message> classObject) {
        for (final MessageType messageType : MessageType.values()) {
            if (messageType.getAssociatedClass().equals(classObject)) {
                return messageType;
            }
        }

        final String exceptionMessage = String.format(
                "Unable to find MessageType for %s", classObject.getName());
        throw new UnableToFindMessageTypeException(exceptionMessage);
    }
}
