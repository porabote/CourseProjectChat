package messages;

public class MessageBuilder {

    private String senderName;
    private String content;
    private long timestamp;

    public MessageBuilder setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public MessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Message create() {

        // TODO check is fields not empty

        return new Message(
                this.senderName,
                this.content,
                this.timestamp);
    }
}
