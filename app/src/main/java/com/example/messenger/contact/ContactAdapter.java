package com.example.messenger.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;

import java.util.LinkedList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mData;
    private Context context;

    // 完成类ContactViewHolder
    // 使用itemView.findViewById()方法来寻找对应的控件
    // TODO

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        ImageView avatar;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_icon_contact);
            nickname = itemView.findViewById(R.id.text_view_contact);

        }

    }

    public ContactAdapter(List<Contact> data, Context context) {
        this.context = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_contact, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // TODO
        Contact contact = mData.get(position);
        holder.avatar.setImageResource(contact.getAvatarIcon());
        holder.nickname.setText(contact.getNickname());
    }

    public void setData(List<Contact> data) {
        mData.clear();
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // TODO
        return mData.size();
    }
}
