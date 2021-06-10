package com.example.messenger.models;

public class Chat {

    private final String nickname;
    private final String lastSpeak;
    private final int avatarIcon;
    private final String lastSpeakTime;

    public Chat(String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public String getLastSpeak() {
        return lastSpeak;
    }

    public String getLastSpeakTime() {
        return lastSpeakTime;
    }

    public String getNickname() {
        return nickname;
    }
}
