package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.activities.MainActivity;
import com.example.messenger.R;
import com.example.messenger.adapters.MessagesAdapter;
import com.example.messenger.models.Contact;
import com.example.messenger.models.MessageModel;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.LinkedList;


public class ChatRoomFragment extends Fragment {
    private ImageView mOtherUserAvatar;
    private TextView mChatName;
    private RecyclerView mRecyclerView;
    private ImageView mBackButton;

    ImageView mUseCamera, mUseGallery, mUseRecorder, mSendMessage, mSendLocation;
    private MainViewModel mMainViewModel;

    private Contact mCurrentContact;
    private UserModel myUser;


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
        mBackButton = view.findViewById(R.id.iv_back_chat_room);
        mBackButton.setImageResource(R.drawable.ic_baseline_back);

        mUseCamera = (ImageView) view.findViewById(R.id.iv_use_camera_message_room);
        mUseGallery = (ImageView)view.findViewById(R.id.iv_use_gallery_message_room);
        mUseRecorder = (ImageView)view.findViewById(R.id.iv_use_recorder_message_room);
        mSendMessage = (ImageView) view.findViewById(R.id.iv_send_message_room);
        mSendMessage.setImageResource(R.drawable.ic_baseline_send);
        mSendLocation = (ImageView)view.findViewById(R.id.iv_send_location_message_room);
        mSendLocation.setImageResource(R.drawable.ic_baseline_add_location);

        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        mCurrentContact = mMainViewModel.getCurrentContactMutableLiveData().getValue();
        int fragmentBeforeClick = mMainViewModel.getFragmentBeforeClick();

        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();

        mChatName.setText(mCurrentContact.getNickname());
        mOtherUserAvatar.setImageResource(mCurrentContact.getAvatarIcon());
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentBeforeClick == 1) {
                    setCurrentFragment(new ChatsFragment());
                }
                else {
                    setCurrentFragment(new ContactsFragment());

                }
            }
        });

        LinkedList<MessageModel> messages = new LinkedList<>();
        messages.add(new MessageModel(1,3, mCurrentContact.getNickname(), "How are you?", mCurrentContact.getAvatarIcon()));
        messages.add(new MessageModel(2,myUser.getId(), myUser.getRealName(), "I am fine. and you?", myUser.getAvatar()));
        messages.add(new MessageModel(3,3, mCurrentContact.getNickname(), "Mee too, thanks!", mCurrentContact.getAvatarIcon()));
        messages.add(new MessageModel(4,myUser.getId(), myUser.getRealName(), "See you soon", myUser.getAvatar()));

        MessagesAdapter messagesAdapter = new MessagesAdapter(messages, getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(messagesAdapter);



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