package com.example.messenger.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.messenger.activities.MainActivity;
import com.example.messenger.R;
import com.example.messenger.adapters.MessagesAdapter;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.Contact;
import com.example.messenger.models.MessageModel;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.LinkedList;


public class ChatRoomFragment extends Fragment {
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int REQUEST_IMAGE_CAPTURE = 2000;
    static final int REQUEST_VIDEO_CAPTURE = 3000;

    private ImageView mOtherUserAvatar;
    private TextView mChatName;
    private RecyclerView mRecyclerView;
    private ImageView mBackButton;
    private EditText mMessageText;
    private ImageView mUseCamera, mUseGallery, mUseRecorder, mSendMessage, mSendLocation;
    private MainViewModel mMainViewModel;

    private VideoView mVideoView;

    private Contact mCurrentContact;
    private UserModel myUser;
    private MessageModel mNewMessage;

    private LinkedList<MessageModel> mMessages;
    private MessagesAdapter messagesAdapter;

    private String myAvatar;

    public ChatRoomFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).hideBotNavAndSearchBar();

        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOtherUserAvatar = view.findViewById(R.id.iv_avatar_chatroom);
        mOtherUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragmentWithBackFunction(new OtherUserProfile());

            }
        });



        mChatName = view.findViewById(R.id.tv_nickname_chatroom);
        mRecyclerView = view.findViewById(R.id.messages_recyclerview);
        mBackButton = view.findViewById(R.id.iv_back_chat_room);
        mBackButton.setImageResource(R.drawable.ic_baseline_back);

        mUseCamera = (ImageView) view.findViewById(R.id.iv_use_camera_message_room);
        mUseGallery = (ImageView)view.findViewById(R.id.iv_use_gallery_message_room);
        mUseRecorder = (ImageView)view.findViewById(R.id.iv_use_recorder_message_room);
        mSendMessage = (ImageView) view.findViewById(R.id.iv_send_message_room);
        mSendMessage.setImageResource(R.drawable.ic_baseline_send);
        mSendLocation = (ImageView)view.findViewById(R.id.iv_send_location_message_room);
        mSendLocation.setImageResource(R.drawable.ic_baseline_add_location);

        mMessageText = (EditText)view.findViewById(R.id.et_write_message_room) ;

        mVideoView = (VideoView)view.findViewById(R.id.video_view_tufta_video);
        mVideoView.setVisibility(View.GONE);






        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        mCurrentContact = mMainViewModel.getCurrentContactMutableLiveData().getValue();
        int fragmentBeforeClick = mMainViewModel.getFragmentBeforeClick();

        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();

        if (myUser.getAvatarUri()!=null) {
            myAvatar = myUser.getAvatarUri();
        }

        mChatName.setText(mCurrentContact.getNickname());
        mOtherUserAvatar.setImageResource(mCurrentContact.getAvatarIcon());
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentBeforeClick == 1) {
                    setCurrentFragment(new ChatsFragment());
                } else {
                    setCurrentFragment(new ContactsFragment());

                }
            }
        });

        LinkedList<MessageModel> messages = new LinkedList<>();
        messages.add(new MessageModel(1,mCurrentContact.getId(), mCurrentContact.getNickname(), "Nice to add you as my friend!", mCurrentContact.getAvatarIcon()));
        if (myAvatar!=null) {
            mNewMessage = new MessageModel(2,myUser.getId(), myUser.getRealName(), "Nice to add you too!", myUser.getAvatar());
            mNewMessage.setAvatarUri(myAvatar);
        }
        if (mNewMessage!=null) {
            messages.add(mNewMessage);
        }
        messagesAdapter = new MessagesAdapter(messages, getContext());

        mMessages = SecurePrefsHelper.getMessagesOfUserFromSecurePrefs(getActivity(), mCurrentContact.getId());
        if (mMessages != null) {
            messagesAdapter.addData(mMessages);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(messagesAdapter);


        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = mMessageText.getText().toString();
                if(messageText.isEmpty()) {
                    return;
                }

                mMessageText.setText("", TextView.BufferType.EDITABLE);
                mNewMessage = new MessageModel(5, myUser.getId(),  myUser.getRealName(),messageText, myUser.getAvatar());
                if (myAvatar!=null) {
                    mNewMessage.setAvatarUri(myAvatar);
                }
                mMessages.add(mNewMessage);
                SecurePrefsHelper.saveMessagesOfUserInSecurePrefs(mMessages, getActivity(), mCurrentContact.getId());
                messagesAdapter.setData(mNewMessage);
            }
        });

        mUseGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else {
                        pickImageFromGallery();

                    }
                }
                else {
                        pickImageFromGallery();
                }

            }
        }
        );
        mUseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        mUseRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeVideoIntent();
            }
        });

        mVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.start();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).showBotNavAndSearchBar();

    }

    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
    private void setCurrentFragmentWithBackFunction(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flFragment, fragment).addToBackStack("chatRoom").commit();
    }

    private void pickImageFromGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        cameraIntent.setType("image/*");
        startActivityForResult(cameraIntent, IMAGE_PICK_CODE);

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            Log.i("CameraError", "dispatchTakePictureIntent: "+e);
        }
    }
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE)
                 {
            Uri returnUri = data.getData();
            String imageData = returnUri.toString();
            mNewMessage = new MessageModel(6, myUser.getId(), myUser.getRealName(), "", myUser.getAvatar(), imageData);
            if (myUser.getAvatarUri()!=null) {
                mNewMessage.setAvatarUri(myUser.getAvatarUri());

            }
            mMessages.add(mNewMessage);
            SecurePrefsHelper.saveMessagesOfUserInSecurePrefs(mMessages, getActivity(), mCurrentContact.getId());
            messagesAdapter.setData(mNewMessage);

        }
        else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {


            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String bitmapString = BitMapToString(imageBitmap);
            mNewMessage = new MessageModel(6, myUser.getId(), myUser.getRealName(), myUser.getAvatar(), bitmapString );
            if(myUser.getAvatarUri()!=null) {
                mNewMessage.setAvatarUri(myUser.getAvatarUri());
            }
            mMessages.add(mNewMessage);
            SecurePrefsHelper.saveMessagesOfUserInSecurePrefs(mMessages, getActivity(), mCurrentContact.getId());
            messagesAdapter.setData(mNewMessage);

        }
        else  if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            Uri videoUri = data.getData();
            Log.i("Arzaqantsyan", "video uri: " + videoUri.toString());
            MediaController mediaController = new MediaController(getActivity());
//            mediaController.setAnchorView(mVideoView);

            mVideoView.setVideoURI(videoUri);
            mVideoView.setVisibility(View.VISIBLE);
            mVideoView.setMediaController(mediaController);
            mVideoView.requestFocus();
            mVideoView.start();

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(getActivity(), "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }


}