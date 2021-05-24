package com.example.homework2.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework2.R;

import java.util.LinkedList;

public class  ChatAdapter extends BaseAdapter {

    private LinkedList<Chat> data;
    private Context context;

    public ChatAdapter(LinkedList<Chat> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_chat, parent, false);
        Chat chat = data.get(position);

        ImageView avatar = convertView.findViewById(R.id.avatar_icon);
        TextView nickname = convertView.findViewById(R.id.nickname_text);
        TextView lastSpeakTime = convertView.findViewById(R.id.last_speak_time_text);
        TextView lastSpeakText = convertView.findViewById(R.id.last_speak_text);

        lastSpeakTime.setText(chat.getLastSpeakTime());
        lastSpeakText.setText(chat.getLastSpeak());
        nickname.setText(chat.getNickname());
        avatar.setImageResource(chat.getAvatarIcon());

        return convertView;
    }
}
