package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Contact;
import com.example.messenger.models.Discover;
import com.example.messenger.adapters.DiscoverAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DiscoverFragment extends Fragment {
    private RecyclerView recyclerView;
    ImageView mAddPost;

    public DiscoverFragment() {
     }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.discover_recyclerview);

        mAddPost = (ImageView)view.findViewById(R.id.iv_add_post_discover);
        mAddPost.setImageResource(R.drawable.ic_baseline_add_post);

        LinkedList<Discover> discovers = SecurePrefsHelper.getDiscoversFromSecurePrefs(getActivity());
        DiscoverAdapter discoverAdapter = new DiscoverAdapter(discovers, getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(discoverAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
}