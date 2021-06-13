package com.example.messenger.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.fragments.ChatRoomFragment;
import com.example.messenger.models.Chat;
import com.example.messenger.models.Contact;
import com.example.messenger.viewmodels.MainViewModel;


import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder> {
    List<Chat> mData;
    Context context;

    MainViewModel mMainViewModel;


    public static class ChatsViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        TextView lastSpeakTime;
        TextView lastSpeakText;
        ImageView avatar;
        LinearLayout rootLayout;

        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.iv_avatar_chat_chats);
            nickname = itemView.findViewById(R.id.tv_nickname_chats);
            lastSpeakTime = itemView.findViewById(R.id.tv_last_speak_time_text);
            lastSpeakText = itemView.findViewById(R.id.tv_last_speak_text_chats);
            rootLayout =itemView.findViewById(R.id.layout_root_chat);


        }

    }
    public ChatsAdapter(List<Chat> data, Context context){
        mData = data;
        this.context = context;
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

    }
    @NonNull
    @Override
    public ChatsAdapter.ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_chat, parent, false);

        return new ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        Chat chat = mData.get(position);
        Contact contact = new Contact( chat.getNickname(), chat.getAvatarIcon(),chat.getId());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainViewModel.setCurrentContactMutableLiveData(contact);
                mMainViewModel.setFragmentBeforeClick(1);
                setCurrentFragment(new ChatRoomFragment());
            }
        });
        holder.avatar.setImageResource(chat.getAvatarIcon());
        holder.nickname.setText(chat.getNickname());
        holder.lastSpeakTime.setText(chat.getLastSpeakTime());
        holder.lastSpeakText.setText(chat.getLastSpeak());


    }

    public void setData(List<Chat> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    private void setCurrentFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flFragment, fragment).addToBackStack("chatRoom").commit();
    }

    private AppCompatActivity getActivity() {
        return (AppCompatActivity) context;
    }
}
