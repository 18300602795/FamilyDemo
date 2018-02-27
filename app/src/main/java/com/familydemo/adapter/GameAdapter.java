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

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder> {
    private Context mContext;

    public GameAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public GameAdapter.GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_game, parent, false);
        return new GameHolder(view);
    }

    @Override
    public void onBindViewHolder(GameAdapter.GameHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class GameHolder extends RecyclerView.ViewHolder {
        public GameHolder(View itemView) {
            super(itemView);

        }
    }

}
