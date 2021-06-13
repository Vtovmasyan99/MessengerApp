package com.example.messenger.models;

public class Chat {

    private  String nickname;
    private  String lastSpeak;
    private  int avatarIcon;
    private  String lastSpeakTime;
    private int id;

    public Chat(String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }
    public Chat(String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime, int id ) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setLastSpeak(String lastSpeak) {
        this.lastSpeak = lastSpeak;
    }
}
