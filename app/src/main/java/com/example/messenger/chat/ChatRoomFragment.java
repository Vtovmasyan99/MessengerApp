package com.example.messenger.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.MainActivity;
import com.example.messenger.R;


public class ChatRoomFragment extends Fragment {
    ImageView mOtherUserAvatar;
    TextView mChatName;
    RecyclerView mRecyclerView;



    public ChatRoomFragment() {
       // Required empty public constructor
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


        String chatterName = "John Cole";
        mChatName.setText(chatterName);
        mOtherUserAvatar.setImageResource(R.drawable.friend);




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).showBotNavAndSearchBar();

    }
}