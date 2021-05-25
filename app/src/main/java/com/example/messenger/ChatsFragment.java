package com.example.messenger;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.messenger.chat.Chat;
import com.example.messenger.chat.ChatAdapter;

import java.util.LinkedList;


public class ChatsFragment extends Fragment {
    private ChatAdapter chatAdapter;
    private LinkedList<Chat> data;
    private ListView listView;

    public ChatsFragment() {
        // Required empty public constructor
    }


    public static ChatsFragment newInstance() {
        ChatsFragment fragment = new ChatsFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = getView().findViewById(R.id.listview);
        Context context = getActivity();

        data = new LinkedList<>();
        data.add(new Chat(getString(R.string.nickname1), R.drawable.avatar_male, getString(R.string.sentence1), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname2), R.drawable.avatar_male, getString(R.string.sentence2), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname3), R.drawable.avatar_male, getString(R.string.sentence3), "2021/01/03"));
        data.add(new Chat(getString(R.string.nickname4), R.drawable.avatar_male, getString(R.string.sentence4), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname5), R.drawable.avatar_male, getString(R.string.sentence5), "2020/01/01"));
        data.add(new Chat(getString(R.string.nickname6), R.drawable.avatar_female, getString(R.string.sentence6), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname7), R.drawable.avatar_female, getString(R.string.sentence7), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname8), R.drawable.avatar_female, getString(R.string.sentence8), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname9), R.drawable.avatar_female, getString(R.string.sentence9), "2021/01/01"));
        data.add(new Chat(getString(R.string.nickname10), R.drawable.avatar_female, getString(R.string.sentence10), "2021/01/01"));


        ChatAdapter adapter = new ChatAdapter(data, context);
        listView.setAdapter(adapter);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                chatAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;

    };
}