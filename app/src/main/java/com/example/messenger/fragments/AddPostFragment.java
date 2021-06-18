package com.example.messenger.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.messenger.R;
import com.example.messenger.activities.MainActivity;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Discover;
import com.example.messenger.models.UserModel;

import java.time.LocalDate;
import java.util.LinkedList;

public class AddPostFragment extends Fragment {
    private static final int IMAGE_PICK_CODE = 1000;

    ImageView mImageView;
    Button mPost;
    Button mAddPhoto;
    EditText mPostText;

    String mImageUri;
    UserModel myUser;
    Discover newPost;




    public AddPostFragment() {
    }

    public static AddPostFragment newInstance() {
        return new AddPostFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myUser = SecurePrefsHelper.getMyUserInSecurePrefs(getActivity());
        mImageView = (ImageView)view.findViewById(R.id.iv_image_new_post);
        mPost = (Button)view.findViewById(R.id.btn_post_new_post);
        mAddPhoto = (Button)view.findViewById(R.id.btn_add_photo_new_post);
        mPostText = (EditText)view.findViewById(R.id.et_text_new_post);
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postText = mPostText.getText().toString();
                if (postText.isEmpty()) {
                    CharSequence text = "Post description should is empty!!";
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    return;
                }
                getActivity().getSupportFragmentManager().popBackStack();
                if(myUser.getAvatarUri()!=null) {
                    if(mImageUri!= null) {
                        newPost = new Discover(10, myUser.getRealName(), myUser.getAvatarUri(), postText, LocalDate.now().toString(), mImageUri);

                    }
                    else {
                        newPost = new Discover(10, myUser.getRealName(), myUser.getAvatarUri(), postText,LocalDate.now().toString());
                    }
                }
                else {
                    if(mImageUri!= null) {
                        newPost = new Discover(10, myUser.getRealName(), myUser.getAvatar(), postText, LocalDate.now().toString(), mImageUri);

                    }
                    else {
                        newPost = new Discover(10, myUser.getRealName(), myUser.getAvatar(), postText,LocalDate.now().toString());
                    }
                }
                LinkedList<Discover> discovers = SecurePrefsHelper.getDiscoversFromSecurePrefs(getActivity());
                discovers.add(0,newPost);
                SecurePrefsHelper.saveDiscoversInSecurePrefs(discovers,getActivity());

                setCurrentFragment(new DiscoverFragment());
                ((MainActivity)getActivity()).showBotNav();
//                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        mAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
    }
    private void pickImageFromGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        cameraIntent.setType("image/*");
        startActivityForResult(cameraIntent, IMAGE_PICK_CODE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            mImageUri = uri.toString();
            mImageView.setImageURI(uri);
            mImageView.setVisibility(View.VISIBLE);

        }
    }
    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}