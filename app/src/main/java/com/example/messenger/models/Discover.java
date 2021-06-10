package com.example.messenger.models;

import java.util.ArrayList;

public class Discover {
    private int avatarIcon;
    private String nickname;
    private String text;
    private String publishedTime;
    private ArrayList<Integer> images;

    public Discover(String nickname, int avatarIcon, String text, String publishedTime, ArrayList<Integer> images) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.text = text;
        this.publishedTime = publishedTime;
        this.images = images;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getText() {
        return text;
    }

    public int getImageCount() {
        return images.size();
    }
}
