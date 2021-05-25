package com.example.messenger.contact;


public class Contact {
    private String nickname;
    private int avatarIcon;

    public Contact(String nickname, int avatarIcon) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public String getNickname() {
        return nickname;
    }
}
