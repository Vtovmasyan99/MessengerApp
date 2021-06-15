package com.example.messenger.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.R;
import com.example.messenger.activities.MainActivity;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;

public class EditProfileFragment extends Fragment {
    private static final int IMAGE_PICK_CODE = 1000;


    ImageView mBackButton, mAvatar;
    Button mSaveButton, mChangeAvatar;
    EditText mSetUsername, mSetPassword, mSetRealName, mSetBirthday;
    UserModel myUser;
    MainViewModel mMainViewModel;


    public EditProfileFragment() {
    }


    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                setCurrentFragment(new ProfileFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).hideBotNavAndSearchBar();
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();

        mSetPassword = (EditText)view.findViewById(R.id.et_password_edit_profile);
        mSetUsername  = (EditText)view.findViewById(R.id.et_username_edit_profile);
        mSetRealName = (EditText)view.findViewById(R.id.et_real_name_edit_profile);
        mSetBirthday = (EditText)view.findViewById(R.id.et_birthday_edit_profile);

        mSetUsername.setText(myUser.getUsername(), TextView.BufferType.EDITABLE);
        mSetRealName.setText(myUser.getRealName(), TextView.BufferType.EDITABLE);
        mSetPassword.setText(myUser.getPassword(), TextView.BufferType.EDITABLE);
        mSetBirthday.setText(myUser.getBirthday(), TextView.BufferType.EDITABLE);

        mAvatar = (ImageView)view.findViewById(R.id.iv_avatar_edit_profile);
        if (myUser.getAvatarUri()!=null) {
            mAvatar.setImageURI(Uri.parse(myUser.getAvatarUri()));
        }
        else {

            mAvatar.setImageResource(myUser.getAvatar());
        }

        mBackButton = (ImageView) view.findViewById(R.id.iv_back_edit_profile);
        mBackButton.setImageResource(R.drawable.ic_baseline_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new ProfileFragment());
            }
        });

        mSaveButton = (Button)view.findViewById(R.id.btn_save_edit_profile);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUser.setUsername(mSetUsername.getText().toString());
                myUser.setPassword(mSetPassword.getText().toString());
                myUser.setRealName(mSetRealName.getText().toString());
                myUser.setBirthday(mSetBirthday.getText().toString());
                mMainViewModel.setMyUserMutableLiveData(myUser);
                setCurrentFragment(new ProfileFragment());
                SecurePrefsHelper.saveMyUserInSecurePrefs(myUser, getActivity());
            }
        });
        mChangeAvatar = (Button)view.findViewById(R.id.iv_change_avatar_edit_profile);
        mChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).showBotNavAndSearchBar();

    }
    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
    private void pickImageFromGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        startActivityForResult(cameraIntent, 1000);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode==IMAGE_PICK_CODE ) {
            Uri imageUri = data.getData();
            mAvatar.setImageURI(imageUri);
            myUser.setAvatarUri(imageUri.toString());
            mMainViewModel.setMyUserMutableLiveData(myUser);
            SecurePrefsHelper.saveMyUserInSecurePrefs(myUser, getActivity());
        }
    }

}