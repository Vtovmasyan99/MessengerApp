package com.example.messenger.models;

import java.time.LocalDateTime;

public class MessageModel {
    private int messageId;
    private String senderUsername;
    private int senderId;
    private LocalDateTime messageDateTime;
    private String messageText;
    private String fileURL;
    MessageModel(int messageId, int senderId, String senderUsername, String messageText) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.messageText = messageText;
        this.messageDateTime = LocalDateTime.now();
    }
    MessageModel(int messageId, int senderId, String senderUsername, String messageText, String fileURL) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.messageText = messageText;
        this.fileURL = fileURL;
        this.messageDateTime = LocalDateTime.now();
    }

    public int getSenderId() {
        return senderId;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getFileURL() {
        return fileURL;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }
}
