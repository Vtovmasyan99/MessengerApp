package com.example.messenger.models;


public class Contact {
    private String nickname;
    private int avatarIcon;
    private int id;
    private String username;
    private String gender;

    public Contact(String nickname, int avatarIcon) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
    }
    public Contact(String nickname, int avatarIcon, int id) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.id = id;
    }
    public Contact(String nickname, int avatarIcon, int id, String username) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.id = id;
        this.username = username;
    }
    public Contact(String nickname, int avatarIcon, int id, String username, String gender) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.id = id;
        this.username = username;
        this.gender = gender;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }
}
