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

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindHolder> {
    private Context mContext;

    public FindAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public FindHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_find, parent, false);
        return new FindHolder(view);
    }

    @Override
    public void onBindViewHolder(FindHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class FindHolder extends RecyclerView.ViewHolder {
        public FindHolder(View itemView) {
            super(itemView);

        }
    }

}
