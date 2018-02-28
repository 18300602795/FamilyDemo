package com.familydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;

/**
 * Created by Administrator on 2018\2\27 0027.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumHolder> {
    private Context context;

    public ForumAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ForumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_forum, parent, false);
        return new ForumHolder(view);
    }

    @Override
    public void onBindViewHolder(ForumHolder holder, int position) {
        holder.item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.img_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.comment_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.like_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.share_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ForumHolder extends RecyclerView.ViewHolder {
        LinearLayout item_ll;
        ImageView head_img;
        TextView name_tv, read_tv, time_tv, title_tv, count_tv;
        LinearLayout img_ll;
        ImageView img1, img2, img3, img4;
        LinearLayout comment_ll, like_ll, share_ll;

        public ForumHolder(View itemView) {
            super(itemView);
            item_ll = itemView.findViewById(R.id.item_ll);
            head_img = itemView.findViewById(R.id.head_img);
            name_tv = itemView.findViewById(R.id.name_tv);
            read_tv = itemView.findViewById(R.id.read_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            title_tv = itemView.findViewById(R.id.title_tv);
            count_tv = itemView.findViewById(R.id.count_tv);
            img_ll = itemView.findViewById(R.id.img_ll);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            comment_ll = itemView.findViewById(R.id.comment_ll);
            like_ll = itemView.findViewById(R.id.like_ll);
            share_ll = itemView.findViewById(R.id.share_ll);
        }
    }

}
