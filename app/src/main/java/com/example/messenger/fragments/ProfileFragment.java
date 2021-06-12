package com.example.messenger.fragments;

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
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;


public class ProfileFragment extends Fragment {
    private UserModel myUser;
    TextView mRealName;
    TextView mBirthDay;
    TextView mGender;
    TextView mEmail;
    ImageView mAvatar;
    Button mEditProfile;
    MainViewModel mMainViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

//        mUserViewModel.setAvatar(R.drawable.avatar);
//        mUserViewModel.setRealNameMutableLiveData("John Stones");
//        mUserViewModel.setBirthdayMutableLiveData("20/12/1999");
//        mUserViewModel.setGender("Male");
//        mUserViewModel.setEmail("johnstones@gmail.com");

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
//        myUser = new UserModel("username1", "password1", "John Stones", 1,
//                R.drawable.avatar, "male", "user@gmail.com");
        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();
        mRealName = (TextView)view.findViewById(R.id.tv_real_name_profile);
        mBirthDay = (TextView)view.findViewById(R.id.tv_birthday_profile);
        mGender = (TextView)view.findViewById(R.id.tv_gender_profile);
        mEmail = (TextView)view.findViewById(R.id.tv_email_profile);
        mAvatar = (ImageView)view.findViewById(R.id.iv_avatar_profile);
        mEditProfile = (Button)view.findViewById(R.id.btn_edit_profile);

        mRealName.setText(myUser.getRealName());
        mBirthDay.setText(myUser.getBirthday());
        mGender.setText(myUser.getGender());
        mEmail.setText(myUser.getEmail());
        mAvatar.setImageResource(myUser.getAvatar());

        super.onViewCreated(view, savedInstanceState);

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new EditProfileFragment());
            }
        });
    }


    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flFragment, fragment).addToBackStack("editProfile").commit();
    }
}