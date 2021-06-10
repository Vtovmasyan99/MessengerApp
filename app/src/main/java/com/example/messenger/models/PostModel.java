package com.example.messenger.models;

import java.time.LocalDate;

public class PostModel {
    private int postID;
    private int userID;
    private String username;
    private String realName;
    private int avatar;
    private String postText;
    private String postFileURL;

    LocalDate postDate;

    public PostModel(int postID, int userID, String username, String realName, int avatar, String postText, String postFileURL) {
        this.postID = postID;
        this.userID = userID;
        this.username = username;
        this.realName = realName;
        this.avatar = avatar;
        this.postText = postText;
        this.postFileURL = postFileURL;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setPostFileURL(String postFileURL) {
        this.postFileURL = postFileURL;
    }


    //getters

    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getRealName() {
        return realName;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getPostText() {
        return postText;
    }

    public String getPostFileURL() {
        return postFileURL;
    }
}
