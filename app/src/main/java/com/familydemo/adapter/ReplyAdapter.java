package com.familydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.familydemo.R;

/**
 * Created by Administrator on 2018\3\5 0005.
 */

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {
    private Context context;

    public ReplyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReplyViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ReplyViewHolder extends RecyclerView.ViewHolder {

        public ReplyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
