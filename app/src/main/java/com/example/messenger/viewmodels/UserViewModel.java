package com.example.messenger.viewmodels;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> usernameMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> passwordMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> realNameMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> birthdayMutableLiveData = new MutableLiveData<>();

    private String gender;
    private String email;
    private int id;
    private int avatar;
    private boolean isLoggedIn;


    public void setUsernameMutableLiveData(String username) {
        this.usernameMutableLiveData.setValue(username);
    }

    public void setPasswordMutableLiveData(String password) {
        this.passwordMutableLiveData.setValue(password);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setRealNameMutableLiveData(String realName) {
        this.realNameMutableLiveData.setValue(realName);
    }

    public void setBirthdayMutableLiveData(String birthday) {
        this.birthdayMutableLiveData.setValue(birthday);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public MutableLiveData<String> getBirthdayMutableLiveData() {
        return birthdayMutableLiveData;
    }

    public MutableLiveData<String> getPasswordMutableLiveData() {
        return passwordMutableLiveData;
    }

    public MutableLiveData<String> getRealNameMutableLiveData() {
        return realNameMutableLiveData;
    }

    public MutableLiveData<String> getUsernameMutableLiveData() {
        return usernameMutableLiveData;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
