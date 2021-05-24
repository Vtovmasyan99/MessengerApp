package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 修改下面的代码，添加向 settingsFragment的跳转逻辑
        // TODO
        Fragment profileFragment = new ProfileFragment();
        Fragment chatsFragment = new ChatsFragment();
        Fragment contactsFragment = new ContactsFragment();
        Fragment discoverFragment = new DiscoverFragment();
        Fragment settingsFragment = new SettingsFragment();

        setCurrentFragment(chatsFragment); // 初始的Fragment为chatsFragment

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.chats:
                            setCurrentFragment(chatsFragment);
                            return true;
                        case R.id.contacts:
                            setCurrentFragment(contactsFragment);
                            return true;
                        case R.id.discover:
                            setCurrentFragment(discoverFragment);
                            return true;
                        case R.id.profile:
                            setCurrentFragment(profileFragment);
                            return true;
                    }
                    return false;
                }
        );
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}