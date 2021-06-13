package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Chat;
import com.example.messenger.adapters.ChatsAdapter;
import com.example.messenger.models.MessageModel;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChatsFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainViewModel mMainViewModel;
    List<Chat> mChats ;


    public ChatsFragment() {
    }


    public static ChatsFragment newInstance() {
        ChatsFragment fragment = new ChatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.chats_recylerview);
        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);


        mChats = SecurePrefsHelper.getChatsFromSecurePrefs(getActivity());
        for (Chat chat:mChats) {
            int chatterId = chat.getId();
            LinkedList<MessageModel> messages = SecurePrefsHelper.getMessagesOfUserFromSecurePrefs(getActivity(), chatterId);
            if(messages.size()>0) {
                chat.setLastSpeak(messages.getLast().getMessageText());
            }
           
        }

        ChatsAdapter chatsAdapter = new ChatsAdapter(mChats, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatsAdapter);

        mMainViewModel.getSearchInputMutableLiveData().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String searchInput) {
                if (searchInput != null) {
                    List<Chat> searchedChatsList = new ArrayList<>();

                    if (searchInput.isEmpty()) {
                        chatsAdapter.setData(mChats);
                    } else {
                        for (Chat chat : mChats) {
                            if (chat.getNickname().toLowerCase().startsWith(searchInput)) {
                                searchedChatsList.add(chat);
                            }
                        }
                        chatsAdapter.setData(searchedChatsList);


                    }
                }
            }


        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mMainViewModel.setSearchInputMutableLiveData(null);
    }
}