package com.example.messenger.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.models.MessageModel;
import com.example.messenger.models.UserModel;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.LinkedList;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LinkedList<MessageModel> data;
    private Context context;
    public static final int MY_MESSAGE = 0;
    public static final int OTHER_MESSAGE = 1;
    private MainViewModel mMainViewModel;
    private UserModel myUser;
    public MessagesAdapter(LinkedList<MessageModel> data, Context context) {
        this.data = data;
        this.context = context;
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        myUser = mMainViewModel.getMyUserMutableLiveData().getValue();
    }
    @Override
    public int getItemViewType (int position){
        MessageModel messageModel = data.get(position);
        if (messageModel.getSenderId() == myUser.getId()) {
            return MY_MESSAGE;
        }
        else return OTHER_MESSAGE;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_my_message, parent,false);
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_recycle_message, parent,false);
        if (viewType == MY_MESSAGE) {
            return new MessageViewHolder(view);
        }
        else {
            return new MessageViewHolder2(view2);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = data.get(position);
        if (holder.getItemViewType()==MY_MESSAGE) {
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            messageViewHolder.avatar.setImageResource(messageModel.getAvatar());
            messageViewHolder.nickname.setText(messageModel.getSenderUsername());
            messageViewHolder.messageText.setText(messageModel.getMessageText());
            messageViewHolder.messageDate.setText(messageModel.getMessageDateTime());
            if(messageModel.getImageSendUri() !=null) {
                messageViewHolder.picture.setImageURI(Uri.parse(messageModel.getImageSendUri()));
                messageViewHolder.picture.setVisibility(View.VISIBLE);
                messageViewHolder.messageContainer.setVisibility(View.GONE);
            }
            else if (messageModel.getImageSendBitmap() !=null){
                Bitmap imageBitmap = StringToBitMap(messageModel.getImageSendBitmap());
                messageViewHolder.picture.setImageBitmap(imageBitmap);
                messageViewHolder.picture.setVisibility(View.VISIBLE);
                messageViewHolder.messageContainer.setVisibility(View.GONE);


            }
            else{
                messageViewHolder.picture.setVisibility(View.GONE);
                messageViewHolder.messageContainer.setVisibility(View.VISIBLE);

            }
            messageViewHolder.filename.setVisibility(View.GONE);

        }
        else {
            MessageViewHolder2 messageViewHolder2 = (MessageViewHolder2) holder;
            messageViewHolder2.avatar.setImageResource(messageModel.getAvatar());
            messageViewHolder2.nickname.setText(messageModel.getSenderUsername());
            messageViewHolder2.messageText.setText(messageModel.getMessageText());
            messageViewHolder2.messageDate.setText(messageModel.getMessageDateTime());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView messageDate;
        TextView messageText;
        TextView nickname;
        TextView filename;
        ImageView picture;
        LinearLayout messageContainer;
        public MessageViewHolder (@NonNull View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.iv_my_avatar_message_room);
            messageDate = (TextView)itemView.findViewById(R.id.tv_my_message_time_chatroom);
            messageText = (TextView)itemView.findViewById(R.id.tv_my_message_text_chatroom);
            nickname = (TextView)itemView.findViewById(R.id.tv_my_nickname_message_room);
            filename = (TextView)itemView.findViewById(R.id.tv_filename_chatroom);
            picture = (ImageView)itemView.findViewById(R.id.iv_my_image_send_chatroom);
            messageContainer = (LinearLayout)itemView.findViewById(R.id.linear_layout_my_message_container);
        }
    }
    public static class MessageViewHolder2 extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView messageDate;
        TextView messageText;
        TextView nickname;
        public MessageViewHolder2 (@NonNull View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.iv_avatar_message_room);
            messageDate = (TextView)itemView.findViewById(R.id.tv_message_time_chatroom);
            messageText = (TextView)itemView.findViewById(R.id.tv_message_text_chatroom);
            nickname = (TextView)itemView.findViewById(R.id.tv_nickname_message_room);

        }
    }
    public void setData(MessageModel newData) {
        this.data.add(newData);
        notifyDataSetChanged();
    }
    public void addData(LinkedList<MessageModel> newData) {
        this.data.addAll(newData);
        notifyDataSetChanged();

    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    private AppCompatActivity getActivity() {
        return (AppCompatActivity) context;
    }
}
