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

public class PhotoAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public PhotoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_big, parent, false);
            return new PhotoHolder(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_two, parent, false);
            return new PhotoHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_normal, parent, false);
            return new PhotoHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 2;
        }
        if (position == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        public PhotoHolder(View itemView) {
            super(itemView);
        }
    }

    class BogPhotoHolder extends RecyclerView.ViewHolder {
        public BogPhotoHolder(View itemView) {
            super(itemView);
        }
    }

    class TwoPhotoHolder extends RecyclerView.ViewHolder {

        public TwoPhotoHolder(View itemView) {
            super(itemView);
        }
    }


}
