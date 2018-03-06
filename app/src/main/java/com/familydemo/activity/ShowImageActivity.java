package com.familydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.ShowImgAdapter;
import com.familydemo.fragment.BaseFragment;
import com.familydemo.fragment.ShowImgFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\3\1 0001.
 */

public class ShowImageActivity extends BaseActivity {

    private ViewPager show_pager;
    private ShowImgAdapter imgAdapter;
    private List<BaseFragment> fragments;
    private ArrayList<String> imgs;
    private TextView num_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black), 0);
        setContentView(R.layout.activity_show_img);
        fragments = new ArrayList<>();
        imgs = getIntent().getStringArrayListExtra("imgs");
        num_tv = findViewById(R.id.num_tv);
        if (imgs.size() == 1) {
            num_tv.setVisibility(View.GONE);
        }
        String num = "1/" + imgs.size();
        num_tv.setText(num);
        show_pager = findViewById(R.id.show_pager);
        for (int i = 0; i < imgs.size(); i++) {
            ShowImgFragment imgFragment = new ShowImgFragment();
            imgFragment.showImg(imgs.get(i));
            fragments.add(imgFragment);
        }
        imgAdapter = new ShowImgAdapter(getSupportFragmentManager(), fragments);
        show_pager.setAdapter(imgAdapter);
        show_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String num = (position + 1) + "/" + imgs.size();
                num_tv.setText(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
