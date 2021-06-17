package com.example.messenger.models;

import java.util.ArrayList;

public class Discover {
    private int postId;
    private int avatarIcon;
    private String nickname;
    private String postText;
    private String postDate;
    private int imageLocal;
    private String imageBitmap;

    public Discover(int postId, String nickname, int avatarIcon, String text, String publishedTime, int imageLocal) {
        this.postId = postId;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageLocal = imageLocal;
    }
    public Discover(int postId, String nickname, int avatarIcon, String text, String publishedTime, String imageBitmap) {
        this.nickname = nickname;
        this.postId = postId;
        this.avatarIcon = avatarIcon;
        this.postText = text;
        this.postDate = publishedTime;
        this.imageBitmap = imageBitmap;
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

    public String getImageBitmap() {
        return imageBitmap;
    }

    public int getPostId() {
        return postId;
    }

    public String getPostText() {
        return postText;
    }
}
