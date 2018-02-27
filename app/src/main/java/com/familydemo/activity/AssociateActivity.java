package com.familydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.bean.CardBean;
import com.familydemo.callback.CardCallback;
import com.jaeger.library.StatusBarUtil;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\2\24 0024.
 */

public class AssociateActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_back;
    private TextView title_tv;
    private ImageView title_right;

    RecyclerView mRv;
    CommonAdapter<CardBean> mAdapter;
    List<CardBean> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.title_bg), 0);
        initView();
        title_tv.setText("求交往");
        title_back.setOnClickListener(this);
        title_right.setVisibility(View.VISIBLE);
        title_right.setOnClickListener(this);
        title_right.setImageResource(R.drawable.edit_bg);
        initDate();
    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);
        title_right = findViewById(R.id.title_right_iv);
        mRv = findViewById(R.id.card_rv);
    }

    private void initDate() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add(new CardBean());
        }
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        mRv.setAdapter(mAdapter = new CommonAdapter<CardBean>(this, mDatas, R.layout.item_friend_card) {
            public static final String TAG = "zxt/Adapter";

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
                super.onBindViewHolder(holder, position);
            }

            @Override
            public void convert(ViewHolder viewHolder, CardBean cardBean) {
            }
        });

        CardConfig.initConfig(this);

        final CardCallback callback = new CardCallback(mRv, mAdapter, mDatas);

        //测试竖直滑动是否已经不会被移除屏幕
        //callback.setHorizontalDeviation(Integer.MAX_VALUE);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right_iv:
                startActivity(new Intent(this, AccountInfoActivity.class));
                break;
        }
    }
}
