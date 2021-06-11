package com.example.messenger.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.messenger.models.Contact;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> searchInputMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Contact> currentContactMutableLiveData = new MutableLiveData<>();


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
}
