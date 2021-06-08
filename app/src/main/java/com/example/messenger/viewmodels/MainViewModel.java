package com.example.messenger.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> searchInputMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<String> getSearchInputMutableLiveData() {
        return searchInputMutableLiveData;
    }

    public void setSearchInputMutableLiveData(String searchInputMutableLiveData) {
        this.searchInputMutableLiveData.setValue(searchInputMutableLiveData);
    }
}
