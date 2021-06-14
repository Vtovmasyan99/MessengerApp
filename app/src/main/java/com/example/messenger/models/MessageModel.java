package com.example.messenger.models;

import android.net.Uri;

import java.net.URI;
import java.time.LocalDateTime;

public class MessageModel {
    private int messageId;
    private String senderUsername;
    private int senderId;
    private String messageDateTime;
    private String messageText;
    private String fileURL;
    private int avatar;
    private String imageSendUri;
    private String imageSendBitmap;
    public MessageModel(int messageId, int senderId, String senderUsername, String messageText, int avatar) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.messageText = messageText;
        this.messageDateTime = LocalDateTime.now().toString();
        this.avatar = avatar;
    }
    public MessageModel(int messageId, int senderId, String senderUsername, String messageText, int avatar,  String imageSend) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.messageText = messageText;
        this.messageDateTime = LocalDateTime.now().toString();
        this.avatar = avatar;
        this.imageSendUri = imageSend;
    }

    public MessageModel(int messageId, int senderId, String senderUsername,  int avatar, String imageSendBitmap) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.messageDateTime = LocalDateTime.now().toString();
        this.avatar = avatar;
        this.imageSendBitmap = imageSendBitmap;
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

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setImageSendUri(String imageSendUri) {
        this.imageSendUri =imageSendUri;
    }

    public String getImageSendUri() {
        return imageSendUri;
    }

    public String getImageSendBitmap() {
        return imageSendBitmap;
    }
}
