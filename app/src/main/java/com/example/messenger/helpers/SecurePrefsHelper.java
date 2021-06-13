package com.example.messenger.helpers;

import android.app.Activity;
import android.content.Context;

import com.example.messenger.models.Chat;
import com.example.messenger.models.Contact;
import com.example.messenger.models.MessageModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SecurePrefsHelper {

    private static final String PREFERENCE_NAME = "sprefs";
    private static final String SECURE_KEY = "BuildConfig.GOOGLE_API_KEY + BuildConfig.FABRIC_API_KEY";


    // USER DATA
    // =========


    // CREATE
    public static void storeUserDataInSecurePrefs(Contact contact, Activity activity) {
        SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
        sprefs.put("userLoginResponse", new Gson().toJson(contact));
    }

    // READ
    public static Contact getUserDataFromSecurePrefs(Context context) {
        if (context != null) {
            SecurePreferences sprefs = new SecurePreferences(context, PREFERENCE_NAME, SECURE_KEY, true);
            return new Gson().fromJson(sprefs.getString("userLoginResponse"), Contact.class);
        } else {
            return null;
        }
    }

    // DELETE
    public static void removeUserDataFromSecurePrefs(Context context) {
        SecurePreferences sprefs = new SecurePreferences(context, PREFERENCE_NAME, SECURE_KEY, true);
        sprefs.removeValue("userLoginResponse");
    }


    // CONTACTS
    // ======

    // CREATE
    public static void saveContactsInSecurePrefs(List<Contact> contacts, Activity activity) {
        Gson gson = new Gson();

        String contactsAsJson = gson.toJson(contacts);

        if (activity != null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
            sprefs.put("contacts", contactsAsJson);
        }
    }
    public static void saveMessagesOfUserInSecurePrefs(LinkedList<MessageModel> messages, Activity activity, int otherUserId) {
        Gson gson = new Gson();
        String messagesAsJson = gson.toJson(messages);
        if (activity!=null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
            String key = "messages"+otherUserId;
            sprefs.put(key, messagesAsJson);
        }
    }
    public static void saveChatsInSecurePrefs(ArrayList<Chat> chats, Activity activity) {
        Gson gson = new Gson();

        String chatsAsJson = gson.toJson(chats);

        if (activity != null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
            sprefs.put("chats", chatsAsJson);
        }
    }



    // READ - ALL
    public static List<Contact> getContactsFromSecurePrefs(Activity activity) {
        Gson gson = new Gson();

        if (activity != null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
            String contactsAsJson = sprefs.getString("contacts");

            if (contactsAsJson != null) {
                List<Contact> contacts = gson.fromJson(contactsAsJson, new TypeToken<List<Contact>>() {
                }.getType());
                return contacts;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }
    public static LinkedList<MessageModel> getMessagesOfUserFromSecurePrefs(Activity activity, int otherUserId) {
        Gson gson = new Gson();
        if (activity!=null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);

            String key = "messages"+otherUserId;
            String messagesAsJson = sprefs.getString(key);
            if(messagesAsJson!=null) {
                LinkedList<MessageModel> messages = gson.fromJson(messagesAsJson, new TypeToken<LinkedList<MessageModel>>(){}.getType());
                return messages;
            }
            else return new LinkedList<>();

        }
        else return new LinkedList<>();
    }

    public static ArrayList<Chat> getChatsFromSecurePrefs(Activity activity) {
        Gson gson = new Gson();
        if (activity!=null) {
            SecurePreferences sprefs = new SecurePreferences(activity, PREFERENCE_NAME, SECURE_KEY, true);
            String chatsAsJson = sprefs.getString("chats");
            if (chatsAsJson!=null) {
                ArrayList<Chat> chats = gson.fromJson(chatsAsJson, new TypeToken<ArrayList<Chat>>(){
                }.getType());
                return chats;
            }
            else return new ArrayList<>();
        }
        else return new ArrayList<>();
    }

    // DELETE
    public static void removeContactsFromSecurePrefs(Context context) {
        if (context != null) {
            SecurePreferences sprefs = new SecurePreferences(context, PREFERENCE_NAME, SECURE_KEY, true);
            sprefs.removeValue("contacts");
        }
    }

    public static void removeChatsFromSecurePrefs(Context context) {
        if (context!=null) {
            SecurePreferences sprefs = new SecurePreferences(context, PREFERENCE_NAME, SECURE_KEY, true);
            sprefs.removeValue("chats");
        }
    }

}
