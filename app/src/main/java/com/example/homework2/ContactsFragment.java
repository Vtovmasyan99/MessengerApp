package com.example.homework2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework2.contact.Contact;
import com.example.homework2.contact.ContactAdapter;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    private RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.contacts_recylerview);

        // 添加数据，为recyclerView绑定Adapter、LayoutManager
        // 添加数据的样例代码如下:
         LinkedList<Contact> contacts = new LinkedList<>();
         contacts.add(new Contact(getString(R.string.nickname1), R.drawable.avatar_male));
         contacts.add(new Contact(getString(R.string.nickname10), R.drawable.avatar_female));
        contacts.add(new Contact(getString(R.string.nickname2), R.drawable.avatar_male));
        contacts.add(new Contact(getString(R.string.nickname9), R.drawable.avatar_female));
        contacts.add(new Contact(getString(R.string.nickname3), R.drawable.avatar_male));
        contacts.add(new Contact(getString(R.string.nickname8), R.drawable.avatar_female));
        contacts.add(new Contact(getString(R.string.nickname4), R.drawable.avatar_male));
        contacts.add(new Contact(getString(R.string.nickname7), R.drawable.avatar_female));
        contacts.add(new Contact(getString(R.string.nickname5), R.drawable.avatar_male));
        contacts.add(new Contact(getString(R.string.nickname6), R.drawable.avatar_female));
        contacts.add(new Contact(getString(R.string.nickname11), R.drawable.avatar_male));


        // TODO
        ContactAdapter contactAdapter = new ContactAdapter(contacts, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(contactAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }
}