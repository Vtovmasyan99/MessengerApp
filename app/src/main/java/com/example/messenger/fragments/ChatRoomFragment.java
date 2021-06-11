package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.activities.MainActivity;
import com.example.messenger.R;
import com.example.messenger.models.Contact;
import com.example.messenger.viewmodels.MainViewModel;


public class ChatRoomFragment extends Fragment {
    private ImageView mOtherUserAvatar;
    private TextView mChatName;
    private RecyclerView mRecyclerView;
    private Button mBackButton;

    private MainViewModel mMainViewModel;

    private Contact mCurrentContact;


    public ChatRoomFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).hideBotNavAndSearchBar();

        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOtherUserAvatar = view.findViewById(R.id.iv_avatar_chatroom);
        mChatName = view.findViewById(R.id.tv_nickname_chatroom);
        mRecyclerView = view.findViewById(R.id.messages_recyclerview);
        mBackButton = view.findViewById(R.id.btn_back_chat_room);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        mCurrentContact = mMainViewModel.getCurrentContactMutableLiveData().getValue();


        mChatName.setText(mCurrentContact.getNickname());
        mOtherUserAvatar.setImageResource(mCurrentContact.getAvatarIcon());
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new ContactsFragment());
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).showBotNavAndSearchBar();

    }

    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}