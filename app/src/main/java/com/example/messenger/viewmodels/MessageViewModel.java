package com.example.messenger.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MessageViewModel extends ViewModel {
    private MutableLiveData<String> messageTextMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> senderUsernameMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> fileURLMutableLiveData = new MutableLiveData<>();
    private int messageId;
    private int senderId;

    public void setFileURLMutableLiveData(String fileURL) {
        this.fileURLMutableLiveData.setValue(fileURL);
    }

    public void setMessageTextMutableLiveData(String messageText) {
        this.messageTextMutableLiveData.setValue(messageText);
    }

    public void setSenderUsernameMutableLiveData(String senderUsername) {
        this.senderUsernameMutableLiveData.setValue(senderUsername);
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public MutableLiveData<String> getFileURLMutableLiveData() {
        return fileURLMutableLiveData;
    }

    public MutableLiveData<String> getSenderUsernameMutableLiveData() {
        return senderUsernameMutableLiveData;
    }

    public MutableLiveData<String> getMessageTextMutableLiveData() {
        return messageTextMutableLiveData;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSenderId() {
        return senderId;
    }
}
