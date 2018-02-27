package com.familydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ForumHolder extends RecyclerView.ViewHolder {
        public ForumHolder(View itemView) {
            super(itemView);
        }
    }

}
