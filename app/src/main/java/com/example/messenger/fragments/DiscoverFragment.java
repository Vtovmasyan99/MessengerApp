package com.example.messenger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messenger.R;
import com.example.messenger.models.Discover;
import com.example.messenger.adapters.DiscoverAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {
    private RecyclerView recyclerView;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExploreFragment.
     */
    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.discover_recyclerview);

        LinkedList<Discover> discovers = new LinkedList<>();
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        images.add(R.drawable.image4);
        discovers.add(new Discover(getString(R.string.nickname1),R.drawable.avatar_male,"Good morning, today is my best day!","sdf",images));

        ArrayList<Integer> images2 = new ArrayList<>();
        images2.add(R.drawable.image5);
        images2.add(R.drawable.image6);
        images2.add(R.drawable.image7);
        discovers.add(new Discover(getString(R.string.nickname6),R.drawable.avatar_female,"Hello Tsinghua!","sdf",images2));

        ArrayList<Integer> images3 = new ArrayList<>();
        images3.add(R.drawable.image9);
        images3.add(R.drawable.image1);

        discovers.add(new Discover(getString(R.string.nickname2),R.drawable.avatar_male,"Who is with me?","sdf",images3));

        ArrayList<Integer> images1 = new ArrayList<>();
        images1.add(R.drawable.image8);

        discovers.add(new Discover(getString(R.string.nickname7),R.drawable.avatar_female,"Life is good...","sdf",images1));

        ArrayList<Integer> images0 = new ArrayList<>();
        discovers.add(new Discover(getString(R.string.nickname3),R.drawable.avatar_male,getString(R.string.paragraph1),"sdf",images0));
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
}