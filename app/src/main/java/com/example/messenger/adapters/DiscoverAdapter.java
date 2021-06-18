package com.example.messenger.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.models.Discover;
import com.example.messenger.viewmodels.MainViewModel;

import java.util.LinkedList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {
    private LinkedList<Discover> data;
    private Context context;



    public DiscoverAdapter(LinkedList<Discover> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_discover_3, parent, false);
        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        Discover discover = data.get(position);
        if (discover.getAvatarUri()==null) {
            holder.avatar.setImageResource(discover.getAvatarIcon());

        }
        else {
            Uri uri = Uri.parse(discover.getAvatarUri());
            holder.avatar.setImageURI(uri);
        }
        holder.nickname.setText(discover.getNickname());
        holder.postText.setText(discover.getPostText());
        holder.postDate.setText(discover.getPostDate());

        if (discover.getImageUri()!=null) {
            Uri uri = Uri.parse(discover.getImageUri());
            holder.image.setImageURI(uri);
        }
        else if (discover.getImageLocal() !=0){
            holder.image.setImageResource(discover.getImageLocal());
        }
        else return;


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView nickname;
        TextView postDate;
        TextView postText;
        ImageView image;
        Button likeButton, commentButton;


        public DiscoverViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.iv_avatar_discover);
            nickname = (TextView)itemView.findViewById(R.id.tv_nickname_discover);
            postDate = (TextView)itemView.findViewById(R.id.tv_date_discover);
            image = (ImageView)itemView.findViewById(R.id.iv_image_discover);
            likeButton = (Button)itemView.findViewById(R.id.btn_like_discover);
            commentButton = (Button)itemView.findViewById(R.id.btn_comment_discover);
            postText = (TextView)itemView.findViewById(R.id.tv_text_discover);
        }
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
}
