package com.example.messenger.adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.models.Contact;
import com.example.messenger.fragments.ChatRoomFragment;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mData;
    private Context context;
    private MainViewModel mMainViewModel;

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        ImageView avatar;
        LinearLayout rootLayout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_icon_contact);
            nickname = itemView.findViewById(R.id.text_view_contact);
            rootLayout = itemView.findViewById(R.id.layout_root_contact);
        }

    }

    public ContactAdapter(List<Contact> data, Context context) {
        this.context = context;
        this.mData = data;

        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_contact, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mData.get(position);

        holder.avatar.setImageResource(contact.getAvatarIcon());
        holder.nickname.setText(contact.getNickname());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainViewModel.setCurrentContactMutableLiveData(contact);
                mMainViewModel.setFragmentBeforeClick(2);
                setCurrentFragment(new ChatRoomFragment());
            }
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flFragment, fragment).addToBackStack("chatRoom").commit();
    }

    public void setData(List<Contact> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private AppCompatActivity getActivity() {
        return (AppCompatActivity) context;
    }
}
