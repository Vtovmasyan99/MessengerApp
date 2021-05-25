package com.example.messenger.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.R;

import java.util.LinkedList;

public class  ChatAdapter extends BaseAdapter implements Filterable {

    private LinkedList<Chat> data;
    private LinkedList<Chat> dataFull;
    private Context context;

    public ChatAdapter(LinkedList<Chat> data, Context context) {
        this.data = data;
        this.context = context;
        this.dataFull = new LinkedList<Chat>(data);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            LinkedList<Chat> filteredList = new LinkedList<>();
            if (constraint ==null || constraint.length()==0) {
                filteredList.addAll(dataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Chat item: dataFull) {
                    if (item.getNickname().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((LinkedList)results.values);
            notifyDataSetChanged();
        }
    };


}
