package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.R;
import com.example.messenger.activities.MainActivity;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Chat;
import com.example.messenger.models.Contact;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OtherUserProfile extends Fragment {


    private TextView otherUserRealName;
    private Button deleteContact, addContact, startMessage;
    private ImageView avatar;
    private MainViewModel mMainViewModel;
    private Contact mCurrentContact;
    private UserModel mUser;
    private int userIndex;
    private List<Contact> mContacts;

    public OtherUserProfile() {
    }

    public static OtherUserProfile newInstance(String param1, String param2) {
        OtherUserProfile fragment = new OtherUserProfile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mCurrentContact = mMainViewModel.getCurrentContactMutableLiveData().getValue();

        avatar = (ImageView)view.findViewById(R.id.iv_avatar_other_profile);
        otherUserRealName = (TextView)view.findViewById(R.id.tv_real_name_other_profile);
        addContact = (Button)view.findViewById(R.id.btn_add_contact_other_profile);
        deleteContact = (Button)view.findViewById(R.id.btn_delete_contact_other_profile);
        startMessage = (Button)view.findViewById(R.id.btn_send_message_other_profile);
        this.mContacts = SecurePrefsHelper.getContactsFromSecurePrefs(getActivity());
        boolean isCurrentProfileInContacts = false;
        int i=0;
        for (Contact savedContact : mContacts) {
            if (mCurrentContact.getId() == savedContact.getId())
            {
                isCurrentProfileInContacts = true;
                userIndex = i;
                break;

            }
            i++;
        }

        if (!isCurrentProfileInContacts) {
            deleteContact.setVisibility(View.GONE);
            startMessage.setVisibility(View.GONE);
            addContact.setVisibility(View.VISIBLE);
        }
        else {
            deleteContact.setVisibility(View.VISIBLE);
            startMessage.setVisibility(View.VISIBLE);
            addContact.setVisibility(View.GONE);

        }
        int fragmentBeforeClick = mMainViewModel.getFragmentBeforeClick();

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = mCurrentContact;
                ArrayList<Chat> chats = SecurePrefsHelper.getChatsFromSecurePrefs(getActivity());
                Chat chat  = new Chat(mCurrentContact.getNickname(), mCurrentContact.getAvatarIcon(), "", LocalDate.now().toString(), mCurrentContact.getId());
                chats.add(0,chat);
                SecurePrefsHelper.saveChatsInSecurePrefs(chats, getActivity());
                mContacts.add(0, contact);
                SecurePrefsHelper.saveContactsInSecurePrefs(mContacts, getActivity());
                if (fragmentBeforeClick == 1) {
                    setCurrentFragment(new ChatsFragment());
                } else {
                    setCurrentFragment(new ContactsFragment());

                }

            }
        });
        deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Arzaqantsyan", "contact index on contacts: " + userIndex);
                ArrayList<Chat> chats = SecurePrefsHelper.getChatsFromSecurePrefs(getActivity());
                chats.remove(userIndex);
                mContacts.remove(userIndex);
                SecurePrefsHelper.saveChatsInSecurePrefs(chats, getActivity());
                SecurePrefsHelper.saveContactsInSecurePrefs(mContacts, getActivity());
                if (fragmentBeforeClick == 1) {
                    setCurrentFragment(new ChatsFragment());
                } else {
                    setCurrentFragment(new ContactsFragment());

                }

            }
        });



        avatar.setImageResource(mCurrentContact.getAvatarIcon());
        otherUserRealName.setText(mCurrentContact.getNickname());

        startMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new ChatRoomFragment());
            }
        });




    }
    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }

}