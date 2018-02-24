package com.familydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;

/**
 * Created by Administrator on 2018\2\24 0024.
 */

public class FriendActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_back;
    private TextView title_tv;
    private ImageView title_right;

    private Button invite_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        initView();
        title_tv.setText("我的好友");
        title_back.setOnClickListener(this);
        title_right.setVisibility(View.VISIBLE);
        title_right.setOnClickListener(this);

        invite_btn.setOnClickListener(this);
    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);
        title_right = findViewById(R.id.title_right_iv);

        invite_btn = findViewById(R.id.invite_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right_iv:
                startActivity(new Intent(this, AssociateActivity.class));
                break;
            case R.id.invite_btn:
                startActivity(new Intent(this, AssociateActivity.class));
                break;
        }
    }
}
