package com.example.messenger.discover;

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

public class DiscoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LinkedList<Discover> data;
    private Context context;

    public static final int LESS_FOUR_TYPE = 0;
    public static final int FOUR_TYPE = 1;

    public DiscoverAdapter(LinkedList<Discover> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO
        Discover discover = data.get(position);
        if (discover.getImageCount() < 4) {
            return LESS_FOUR_TYPE;
        } else {
            return FOUR_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_discover_3, parent, false);
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_list_discover_4, parent, false);

        if (viewType == LESS_FOUR_TYPE) {
            return new DiscoverViewHolder(view, 3);
        }
        return new DiscoverViewHolder2(view2, 4);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // TODO
        Discover discover = data.get(position);

        switch (holder.getItemViewType()) {
            case LESS_FOUR_TYPE:
                DiscoverViewHolder viewHolder0 = (DiscoverViewHolder)holder;
                viewHolder0.avatar_3.setImageResource(discover.getAvatarIcon());
                viewHolder0.title3.setText(discover.getNickname());
                viewHolder0.description3.setText(discover.getText());
                if (discover.getImageCount() == 2) {
                    viewHolder0.image3_3.setVisibility(View.GONE);
                    viewHolder0.image3_2.setVisibility(View.VISIBLE);
                    viewHolder0.image3_1.setVisibility(View.VISIBLE);

                    viewHolder0.image3_1.setImageResource(discover.getImages().get(0));
                    viewHolder0.image3_2.setImageResource(discover.getImages().get(1));
                } else if (discover.getImageCount() == 1) {
                    viewHolder0.image3_3.setVisibility(View.GONE);
                    viewHolder0.image3_2.setVisibility(View.GONE);
                    viewHolder0.image3_1.setVisibility(View.VISIBLE);

                    viewHolder0.image3_1.setImageResource(discover.getImages().get(0));
                } else if (discover.getImageCount() == 0){
                    viewHolder0.image3_2.setVisibility(View.GONE);
                    viewHolder0.image3_1.setVisibility(View.GONE);
                    viewHolder0.image3_3.setVisibility(View.GONE);
                } else {
                    viewHolder0.image3_2.setVisibility(View.VISIBLE);
                    viewHolder0.image3_1.setVisibility(View.VISIBLE);
                    viewHolder0.image3_3.setVisibility(View.VISIBLE);

                    viewHolder0.image3_1.setImageResource(discover.getImages().get(0));
                    viewHolder0.image3_2.setImageResource(discover.getImages().get(1));
                    viewHolder0.image3_3.setImageResource(discover.getImages().get(2));
                }

                break;

            case FOUR_TYPE:
                DiscoverViewHolder2 viewHolder2 = (DiscoverViewHolder2)holder;
                viewHolder2.avatar_4.setImageResource(discover.getAvatarIcon());
                viewHolder2.title4.setText(discover.getNickname());
                viewHolder2.description4.setText(discover.getText());
                viewHolder2.image4_1.setImageResource(discover.getImages().get(0));
                viewHolder2.image4_2.setImageResource(discover.getImages().get(1));
                viewHolder2.image4_3.setImageResource(discover.getImages().get(2));
                viewHolder2.image4_4.setImageResource(discover.getImages().get(3));
                break;
        }
    }

    @Override
    public int getItemCount() {
        // TODO
        return data.size();
    }

    // TODO: 完成DiscoverViewHolder类
    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {
        public int imageCount;
        TextView title3;
        TextView description3;
        ImageView image3_1;
        ImageView image3_2;
        ImageView image3_3;
        ImageView avatar_3;

        // TODO: 添加其他包含的其他控件
        public DiscoverViewHolder(@NonNull View itemView, int imageCount) {
            super(itemView);
            this.imageCount = imageCount;
            image3_1 = itemView.findViewById(R.id.image_3_1);
            image3_2 = itemView.findViewById(R.id.image_3_2);
            image3_3 = itemView.findViewById(R.id.image_3_3);
            description3 = itemView.findViewById(R.id.description_3);
            title3 = itemView.findViewById(R.id.title_3);
            avatar_3 = itemView.findViewById(R.id.avatar_3);
        }
    }

    // TODO: 完成DiscoverViewHolder类
    public static class DiscoverViewHolder2 extends RecyclerView.ViewHolder {
        public int imageCount;
        TextView title4;
        TextView description4;
        ImageView image4_1;
        ImageView image4_2;
        ImageView image4_3;
        ImageView image4_4;
        ImageView avatar_4;

        // TODO: 添加其他包含的其他控件
        public DiscoverViewHolder2(@NonNull View itemView, int imageCount) {
            super(itemView);
            this.imageCount = imageCount;
            image4_1 = itemView.findViewById(R.id.image_4_1);
            image4_2 = itemView.findViewById(R.id.image_4_2);
            image4_3 = itemView.findViewById(R.id.image_4_3);
            image4_4 = itemView.findViewById(R.id.image_4_4);
            description4 = itemView.findViewById(R.id.description_4);
            title4 = itemView.findViewById(R.id.title_4);
            avatar_4 = itemView.findViewById(R.id.avatar_4);
        }
    }

}
