package madridgug.autoimplement

interface MessageSender {
    /**
     * Sends a message {@link String}
     */
    UUID sendMessage(String message)
    /**
     * Checks whether the message has been received yet or not
     */
    boolean isMessageReceived(UUID messageId)
    /**
     * Checks whether the message has been sent yet or not
     */
    Boolean isMessageSent(UUID messageId)
}
