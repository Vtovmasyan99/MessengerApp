package com.example.messenger.models;

import java.time.LocalDate;

public class UserModel {
    private String username;
    private String password;
    private String realName;
    private int id;
    private String gender;
    private String email;
    private int avatar;
    LocalDate dateJoined;
    private String birthday;
    private String avatarUri;
    public UserModel() {
        dateJoined = LocalDate.now();
        birthday = "Unknown";
    }
    public UserModel(String username, String password, String realName, int id, int avatar, String gender, String email) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.id = id;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.birthday = "20/12/1999";

    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public int getAvatar() {
        return avatar;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }
}
