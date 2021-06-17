package com.example.messenger.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Chat;
import com.example.messenger.models.Contact;
import com.example.messenger.models.MessageModel;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;
import com.example.messenger.fragments.DiscoverFragment;
import com.example.messenger.fragments.ChatsFragment;
import com.example.messenger.fragments.ContactsFragment;
import com.example.messenger.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText searchView;

    private MainViewModel mMainViewModel;
    private UserModel myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        initViews();
        initMyUser();
        initContacts();
        initChats();


        mMainViewModel.setMyUserMutableLiveData(myUser);

        Fragment profileFragment = new ProfileFragment();
        Fragment chatsFragment = new ChatsFragment();
        Fragment contactsFragment = new ContactsFragment();
        Fragment discoverFragment = new DiscoverFragment();

        setCurrentFragment(chatsFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.chats:
                            showSearchContactView();
                            setCurrentFragment(chatsFragment);
                            return true;
                        case R.id.contacts:
                            showSearchContactView();
                            setCurrentFragment(contactsFragment);
                            return true;
                        case R.id.discover:
                            hideSearchContactView();
                            setCurrentFragment(discoverFragment);
                            return true;
                        case R.id.profile:
                            hideSearchContactView();
                            setCurrentFragment(profileFragment);
                            return true;
                    }
                    return false;
                }

        );


        searchView.clearFocus();

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchInput = s.toString().toLowerCase();
                mMainViewModel.setSearchInputMutableLiveData(searchInput);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initContacts() {
        List<Contact> mContacts = new ArrayList<>();
        mContacts.add(new Contact(getString(R.string.nickname1), R.drawable.avatar_male, 2, "username2", "Male"));
        mContacts.add(new Contact(getString(R.string.nickname10), R.drawable.avatar_female, 3, "username3", "Female"));
        mContacts.add(new Contact(getString(R.string.nickname2), R.drawable.avatar_male, 4, "username4", "Male"));
        mContacts.add(new Contact(getString(R.string.nickname9), R.drawable.avatar_female, 5, "username5", "Female"));
        mContacts.add(new Contact(getString(R.string.nickname3), R.drawable.avatar_male, 6, "username6", "Male"));
        mContacts.add(new Contact(getString(R.string.nickname8), R.drawable.avatar_female, 7, "username7", "Female"));
        mContacts.add(new Contact(getString(R.string.nickname4), R.drawable.avatar_male, 8, "username8", "Male"));
        mContacts.add(new Contact(getString(R.string.nickname7), R.drawable.avatar_female, 9, "username9", "Female"));
        mContacts.add(new Contact(getString(R.string.nickname5), R.drawable.avatar_male, 10, "username10", "Male"));
        mContacts.add(new Contact(getString(R.string.nickname6), R.drawable.avatar_female, 11, "username11", "Female"));

        SecurePrefsHelper.saveContactsInSecurePrefs(mContacts, this);


    }



    private void initChats() {
        ArrayList<Chat> mChats = new ArrayList<>();

        mChats.add(new Chat(getString(R.string.nickname1), R.drawable.avatar_male, "", "2021/01/01", 2));
        mChats.add(new Chat(getString(R.string.nickname10), R.drawable.avatar_female, "", "2021/01/01", 3));
        mChats.add(new Chat(getString(R.string.nickname2), R.drawable.avatar_male, "", "2021/01/03", 4));
        mChats.add(new Chat(getString(R.string.nickname9), R.drawable.avatar_female, "", "2021/01/01", 5));
        mChats.add(new Chat(getString(R.string.nickname3), R.drawable.avatar_male, "", "2020/01/01", 6));
        mChats.add(new Chat(getString(R.string.nickname8), R.drawable.avatar_female, "", "2021/01/01", 7));
        mChats.add(new Chat(getString(R.string.nickname4), R.drawable.avatar_male, "", "2021/01/01", 8));
        mChats.add(new Chat(getString(R.string.nickname7), R.drawable.avatar_female,"", "2021/01/01", 9));
        mChats.add(new Chat(getString(R.string.nickname5), R.drawable.avatar_male, "", "2021/01/01", 10));
        mChats.add(new Chat(getString(R.string.nickname6), R.drawable.avatar_female,"", "2021/01/01", 11));

        SecurePrefsHelper.saveChatsInSecurePrefs(mChats, this);

    }


    private void initViews() {
        searchView = findViewById(R.id.et_search_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void initMyUser() {
        if (SecurePrefsHelper.getMyUserInSecurePrefs(this)!=null) {
            myUser = SecurePrefsHelper.getMyUserInSecurePrefs(this);
            mMainViewModel.setMyUserMutableLiveData(myUser);
        }
        else {
            myUser = new UserModel();
            myUser.setRealName("John Stones");
            myUser.setBirthday("20.12.1998");
            myUser.setEmail("John@gmail.com");
            myUser.setAvatar(R.drawable.avatar);
            myUser.setGender("Male");
            myUser.setId(1);
            myUser.setUsername("username1");
            myUser.setPassword("PASSWORD1");
            mMainViewModel.setMyUserMutableLiveData(myUser);
        }

    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }

    public void hideBotNavAndSearchBar() {
        bottomNavigationView.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
    }

    public void showBotNavAndSearchBar() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
    }

    public void hideSearchContactView() {
        searchView.setVisibility(View.GONE);
    }

    public void showSearchContactView() {
        searchView.setVisibility(View.VISIBLE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}