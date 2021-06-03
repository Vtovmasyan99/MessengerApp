package com.example.messenger;

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

import com.example.messenger.chat.Chat;
import com.example.messenger.chat.ChatsAdapter;
import com.example.messenger.contact.MainViewModel;

import java.util.ArrayList;
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
        mChats = new ArrayList<>();

        mChats.add(new Chat(getString(R.string.nickname1), R.drawable.avatar_male, getString(R.string.sentence1), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname2), R.drawable.avatar_male, getString(R.string.sentence2), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname3), R.drawable.avatar_male, getString(R.string.sentence3), "2021/01/03"));
        mChats.add(new Chat(getString(R.string.nickname4), R.drawable.avatar_male, getString(R.string.sentence4), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname5), R.drawable.avatar_male, getString(R.string.sentence5), "2020/01/01"));
        mChats.add(new Chat(getString(R.string.nickname6), R.drawable.avatar_female, getString(R.string.sentence6), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname7), R.drawable.avatar_female, getString(R.string.sentence7), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname8), R.drawable.avatar_female, getString(R.string.sentence8), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname9), R.drawable.avatar_female, getString(R.string.sentence9), "2021/01/01"));
        mChats.add(new Chat(getString(R.string.nickname10), R.drawable.avatar_female, getString(R.string.sentence10), "2021/01/01"));

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