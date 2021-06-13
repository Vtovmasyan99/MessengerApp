package com.example.messenger.helpers;

import android.app.Activity;
import android.content.Context;

import com.example.messenger.models.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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

    // DELETE
    public static void removeContactsFromSecurePrefs(Context context) {
        if (context != null) {
            SecurePreferences sprefs = new SecurePreferences(context, PREFERENCE_NAME, SECURE_KEY, true);
            sprefs.removeValue("contacts");
        }
    }
}
