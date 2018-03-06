package com.familydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.ReplyAdapter;
import com.familydemo.view.CommentHeaderView;
import com.jaeger.library.StatusBarUtil;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

/**
 * Created by Administrator on 2018\3\5 0005.
 */

public class CommentActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_back;
    private TextView title_tv;
    private ImageView title_right;

    private EditText comment_et;
    private TextView comment_tv;
    private TextView send_tv;
    private ReplyAdapter adapter;
    private RecyclerView comment_recycle;
    private CommentHeaderView headerView;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.title_bg), 0);
        initView();
        initDate();
    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);
        title_right = findViewById(R.id.title_right_iv);

        comment_et = findViewById(R.id.comment_et);
        comment_tv = findViewById(R.id.comment_tv);
        send_tv = findViewById(R.id.send_tv);
        comment_recycle = findViewById(R.id.comment_recycle);
    }

    private void initDate() {
        title_tv.setText("评论");
        title_back.setOnClickListener(this);
        title_right.setVisibility(View.VISIBLE);
        title_right.setOnClickListener(this);
        title_right.setImageResource(R.mipmap.share);
        comment_recycle.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ReplyAdapter(context);
        headerView = new CommentHeaderView(context);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(headerView);
        mLoadMoreWrapper = new LoadMoreWrapper(headerAndFooterWrapper);
        comment_recycle.setAdapter(mLoadMoreWrapper);
        comment_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    comment_tv.setVisibility(View.GONE);
                } else {
                    comment_tv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right_iv:
                break;
            case R.id.send_tv:
                break;
        }
    }
}
