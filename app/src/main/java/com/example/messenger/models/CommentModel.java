package com.example.messenger.models;

import java.time.LocalDate;

public class CommentModel {
    private int commentID;
    private String senderUsername;
    private String senderRealName;
    private int senderAvatar;
    private int senderID;
    private String commentText;
    private LocalDate commentDate;

    public CommentModel(int commentID, String commentText, String senderUsername, String senderRealName, int senderAvatar, int senderID) {
        this.commentID = commentID;
        this.commentText = commentText;
        this.senderUsername = senderUsername;
        this.senderRealName = senderRealName;
        this.senderAvatar = senderAvatar;
        this.senderID = senderID;

    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setSenderAvatar(int senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public void setSenderRealName(String senderRealName) {
        this.senderRealName = senderRealName;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getSenderAvatar() {
        return senderAvatar;
    }

    public int getSenderID() {
        return senderID;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getSenderRealName() {
        return senderRealName;
    }
}
