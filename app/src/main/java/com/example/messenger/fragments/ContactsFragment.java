package com.example.messenger.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Contact;
import com.example.messenger.adapters.ContactAdapter;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {
    private RecyclerView recyclerView;

    private MainViewModel mMainViewModel;

    List<Contact> mContacts = new ArrayList<>();



    public ContactsFragment() {
    }


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
        recyclerView = view.findViewById(R.id.contacts_recyclerview);

        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);



        List<Contact> mContacts = new ArrayList<>();

        mContacts.add(new Contact(getString(R.string.nickname1), R.drawable.avatar_male));
        mContacts.add(new Contact(getString(R.string.nickname10), R.drawable.avatar_female));
        mContacts.add(new Contact(getString(R.string.nickname2), R.drawable.avatar_male));
        mContacts.add(new Contact(getString(R.string.nickname9), R.drawable.avatar_female));
        mContacts.add(new Contact(getString(R.string.nickname3), R.drawable.avatar_male));
        mContacts.add(new Contact(getString(R.string.nickname8), R.drawable.avatar_female));
        mContacts.add(new Contact(getString(R.string.nickname4), R.drawable.avatar_male));
        mContacts.add(new Contact(getString(R.string.nickname7), R.drawable.avatar_female));
        mContacts.add(new Contact(getString(R.string.nickname5), R.drawable.avatar_male));
        mContacts.add(new Contact(getString(R.string.nickname6), R.drawable.avatar_female));
        mContacts.add(new Contact(getString(R.string.nickname11), R.drawable.avatar_male));

        SecurePrefsHelper.saveContactsInSecurePrefs(mContacts, getActivity());


        ContactAdapter contactAdapter = new ContactAdapter(mContacts, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(contactAdapter);


        mMainViewModel.getSearchInputMutableLiveData().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String searchInput) {
                if (searchInput != null) {
                    List<Contact> searchedContactsList = new ArrayList<>();

                    if (searchInput.isEmpty()) {
                        contactAdapter.setData(mContacts);
                    } else {
                        for (Contact contact : mContacts) {
                            if (contact.getNickname().toLowerCase().startsWith(searchInput)) {
                                searchedContactsList.add(contact);
                            }
                        }
                        contactAdapter.setData(searchedContactsList);


                    }
                }
            }

        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mMainViewModel.setSearchInputMutableLiveData(null);
    }
}