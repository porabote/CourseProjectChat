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
    private
    long timestamp;
    private MessageStatus status;

    public Message(String senderName, String content, long timestamp) {
        this.senderName = senderName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getContent() {
        return this.content;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getFormatedTimestamp() {
        return new Date(this.timestamp).toString();
    }

    public String toJson() {
        StringBuilder jsonMsg = new StringBuilder("{");
        jsonMsg.append("\"content\":\"" + this.content + "\",");
        jsonMsg.append("\"senderName\":\"" + this.senderName + "\",");
        jsonMsg.append("\"timestamp\":\"" + this.timestamp + "\"");
        jsonMsg.append("}");

        return jsonMsg.toString();
    }
}
