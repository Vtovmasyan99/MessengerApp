package com.example.messenger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.models.MessageModel;

import java.util.LinkedList;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LinkedList<MessageModel> data;
    private Context context;
    public static final int MY_MESSAGE = 0;
    public static final int OTHER_MESSAGE = 1;

    public MessagesAdapter(LinkedList<MessageModel> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getItemViewType (int position){
        MessageModel messageModel = data.get(position);
        if (messageModel.getSenderId() == 1) {
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
            return new MessageViewHolder2(view);
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
            messageViewHolder.messageDate.setText(messageModel.getMessageDateTime().toString());

        }
        else {
            MessageViewHolder2 messageViewHolder2 = (MessageViewHolder2) holder;
            messageViewHolder2.avatar.setImageResource(messageModel.getAvatar());
            messageViewHolder2.nickname.setText(messageModel.getSenderUsername());
            messageViewHolder2.messageText.setText(messageModel.getMessageText());
            messageViewHolder2.messageDate.setText(messageModel.getMessageDateTime().toString());
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
        public MessageViewHolder (@NonNull View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.iv_my_avatar_message_room);
            messageDate = (TextView)itemView.findViewById(R.id.tv_my_message_time_chatroom);
            messageText = (TextView)itemView.findViewById(R.id.tv_my_message_text_chatroom);
            nickname = (TextView)itemView.findViewById(R.id.tv_my_nickname_message_room);

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
}
