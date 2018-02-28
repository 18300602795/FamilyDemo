package com.familydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.familydemo.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class HunterAdapter extends RecyclerView.Adapter<HunterAdapter.HunterHolder> {
    private Context context;

    public HunterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HunterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hunter, parent, false);
        return new HunterHolder(view);
    }

    @Override
    public void onBindViewHolder(HunterHolder holder, int position) {
        holder.expandableTextView.setText("1、每个账号每天最多提交3次返利申请；\n2、每个游戏每天的返利只能提交一次申请，不能重复提交，如果充值金额有变化请去修改已提交的申请（只限制游戏当天充值返利申请的限制，如充值时间不一致的不受限制）\n3、提交申请所提交的内容必须属实，因虚假信息导致返利申请被拒绝，由申请者承担责任。");
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class HunterHolder extends RecyclerView.ViewHolder {
        ExpandableTextView expandableTextView;

        public HunterHolder(View itemView) {
            super(itemView);
            expandableTextView = itemView.findViewById(R.id.expand_text_view);
        }
    }

}
