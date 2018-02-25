package com.familydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.jaeger.library.StatusBarUtil;

/**
 * Created by Administrator on 2018\2\24 0024.
 */

public class RealActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_back;
    private TextView title_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.title_bg), 0);
        initView();
        title_tv.setText("真人认证");
        title_back.setOnClickListener(this);
    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
