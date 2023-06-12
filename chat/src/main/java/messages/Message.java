package messages;

import java.util.Date;

public class Message {
    private int id;
    private int chatId;
    private int senderId;
    private int recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private String timestamp;
    private MessageStatus status;

    public String getRecipientName() {
        return this.recipientName;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getContent() {
        return this.content;
    }

    public String getTimestamp() {
        return this.timestamp;
    }
}
