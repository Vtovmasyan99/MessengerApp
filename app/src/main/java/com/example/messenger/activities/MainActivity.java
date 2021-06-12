package com.example.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.messenger.R;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;
import com.example.messenger.fragments.DiscoverFragment;
import com.example.messenger.fragments.ChatsFragment;
import com.example.messenger.fragments.ContactsFragment;
import com.example.messenger.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText searchView;

    private MainViewModel mMainViewModel;
    private UserModel myUser=new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        initViews();
        initMyUser();

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

    private void initViews() {
        searchView = findViewById(R.id.et_search_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
    private void initMyUser() {
        myUser.setRealName("John Stones");
        myUser.setBirthday("20.12.1998");
        myUser.setEmail("John@gmail.com");
        myUser.setAvatar(R.drawable.avatar);
        myUser.setGender("Male");
        myUser.setId(1);
        myUser.setUsername("username1");
        myUser.setPassword("PASSWORD1");
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

}