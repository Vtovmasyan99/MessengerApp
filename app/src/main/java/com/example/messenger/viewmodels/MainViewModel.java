package com.example.messenger.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.messenger.models.Chat;
import com.example.messenger.models.Contact;
import com.example.messenger.models.UserModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> searchInputMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Contact> currentContactMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Chat> currentChatMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<UserModel> myUserMutableLiveData = new MutableLiveData<>();
    private int fragmentBeforeClick;

    public MutableLiveData<String> getSearchInputMutableLiveData() {
        return searchInputMutableLiveData;
    }

    public void setSearchInputMutableLiveData(String searchInputMutableLiveData) {
        this.searchInputMutableLiveData.setValue(searchInputMutableLiveData);
    }

    public MutableLiveData<Contact> getCurrentContactMutableLiveData() {
        return currentContactMutableLiveData;
    }

    public void setCurrentContactMutableLiveData(Contact currentContactMutableLiveData) {
        this.currentContactMutableLiveData.setValue(currentContactMutableLiveData);
    }


    public MutableLiveData<Chat> getCurrentChatMutableLiveData() {
        return currentChatMutableLiveData;
    }

    public void setCurrentChatMutableLiveData(Chat currentChatMutableLiveData) {
        this.currentChatMutableLiveData.setValue(currentChatMutableLiveData);
    }

    public void setFragmentBeforeClick(int fragmentBeforeClick) {
        this.fragmentBeforeClick = fragmentBeforeClick;
    }

    public int getFragmentBeforeClick() {
        return fragmentBeforeClick;
    }

    public void setMyUserMutableLiveData(UserModel myUserMutableLiveData) {
        this.myUserMutableLiveData.setValue(myUserMutableLiveData);
    }

    public MutableLiveData<UserModel> getMyUserMutableLiveData() {
        return myUserMutableLiveData;
    }
}
