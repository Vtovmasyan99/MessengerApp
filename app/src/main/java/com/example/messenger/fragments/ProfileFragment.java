package com.example.messenger.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.R;
import com.example.messenger.activities.LoginActivity;
import com.example.messenger.activities.MainActivity;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;


public class ProfileFragment extends Fragment {
    private UserModel myUser;
    TextView mRealName;
    TextView mBirthDay;
    TextView mGender;
    TextView mEmail;
    ImageView mAvatar;
    Button mEditProfile, mLogOut;
    MainViewModel mMainViewModel;

    public ProfileFragment() {
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {

        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();
        mRealName = (TextView)view.findViewById(R.id.tv_real_name_profile);
        mBirthDay = (TextView)view.findViewById(R.id.tv_birthday_profile);
        mGender = (TextView)view.findViewById(R.id.tv_gender_profile);
        mEmail = (TextView)view.findViewById(R.id.tv_email_profile);
        mLogOut = (Button)view.findViewById(R.id.btn_logout_profile);

        mAvatar = (ImageView)view.findViewById(R.id.iv_avatar_profile);
        mEditProfile = (Button)view.findViewById(R.id.btn_edit_profile);

        mRealName.setText(myUser.getRealName());
        mBirthDay.setText(myUser.getBirthday());
        mGender.setText(myUser.getGender());
        mEmail.setText(myUser.getEmail());
        if(myUser.getAvatarUri()!=null) {
            mAvatar.setImageURI(Uri.parse(myUser.getAvatarUri()));
        }
        else {
            mAvatar.setImageResource(myUser.getAvatar());
        }

        super.onViewCreated(view, savedInstanceState);

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new EditProfileFragment());
            }
        });
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecurePrefsHelper.removeMyUserFromSecurePrefs(getActivity());
                SecurePrefsHelper.removeContactsFromSecurePrefs(getActivity());
                SecurePrefsHelper.removeChatsFromSecurePrefs(getActivity());
                getActivity().finish();
                Intent myIntent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(myIntent);
            }
        });
    }


    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flFragment, fragment).addToBackStack("editProfile").commit();
    }
}