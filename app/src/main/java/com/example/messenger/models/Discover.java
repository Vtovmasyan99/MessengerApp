package com.example.messenger.models;

import java.util.ArrayList;

public class Discover {
    private int postId;
    private int avatarIcon;
    private String avatarUri;
    private String nickname;
    private String postText;
    private String postDate;
    private int imageLocal;
    private String imageUri;

    public Discover(int postId, String nickname, int avatarIcon, String text, String publishedTime, int imageLocal) {
        this.postId = postId;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageLocal = imageLocal;
    }
    public Discover(int postId, String nickname, int avatarIcon, String text, String publishedTime, String imageUri) {
        this.nickname = nickname;
        this.postId = postId;
        this.avatarIcon = avatarIcon;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageUri = imageUri;
    }
    public Discover(int postId, String nickname, String avatarUri, String text, String publishedTime, String imageUri) {
        this.nickname = nickname;
        this.postId = postId;
        this.avatarUri = avatarUri;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageUri = imageUri;
    }
    public Discover(int postId, String nickname, String avatarUri, String text, String publishedTime) {
        this.nickname = nickname;
        this.postId = postId;
        this.avatarUri = avatarUri;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageLocal = 0;
    }
    public Discover(int postId, String nickname, int avatarIcon, String text, String publishedTime) {
        this.nickname = nickname;
        this.postId = postId;
        this.avatarIcon = avatarIcon;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageLocal = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public int getImageLocal() {
        return imageLocal;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getPostId() {
        return postId;
    }

    public String getPostText() {
        return postText;
    }

    public String getAvatarUri() {
        return avatarUri;
    }
}
