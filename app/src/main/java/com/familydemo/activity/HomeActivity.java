package com.familydemo.activity;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.callback.HomeCallback;
import com.familydemo.fragment.BaseFragment;
import com.familydemo.fragment.FindFragment;
import com.familydemo.fragment.GameFragment;
import com.familydemo.fragment.PhotoFragment;
import com.familydemo.utils.Utils;
import com.familydemo.view.CircleImageView;
import com.familydemo.view.HomePersonSticky;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\2\26 0026.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener, HomeCallback {
    private ViewPager mViewPager;
    private List<BaseFragment> fragments;
    private FragmentPagerAdapter mAdapter;

    private ImageView title_back, title_share, title_menu;
    private CircleImageView photo_img;
    private LinearLayout info_ll, flower_ll;
    private TextView nick_tv, flower_num;
    private ImageView sex_img, real_img, flower_img;
    private HomePersonSticky sticky;
    private LinearLayout game_ll, photo_ll, find_ll;
    private TextView game_num, photo_num, find_num;
    private View game_bg, photo_bg, find_bg;
    private List<View> views;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        initView();
        initDate();
    }

    private void initView() {
        mViewPager = findViewById(R.id.id_stickynavlayout_viewpager);
        photo_img = findViewById(R.id.photo_img);
        info_ll = findViewById(R.id.info_ll);
        flower_ll = findViewById(R.id.flower_ll);
        sticky = findViewById(R.id.sticky);

        title_back = findViewById(R.id.title_back);
        title_share = findViewById(R.id.title_share);
        title_menu = findViewById(R.id.title_menu);
        nick_tv = findViewById(R.id.nick_tv);
        flower_num = findViewById(R.id.flower_num);
        sex_img = findViewById(R.id.sex_img);
        real_img = findViewById(R.id.real_img);
        flower_img = findViewById(R.id.flower_img);
        game_ll = findViewById(R.id.game_ll);
        photo_ll = findViewById(R.id.photo_ll);
        find_ll = findViewById(R.id.find_ll);
        game_num = findViewById(R.id.game_num);
        photo_num = findViewById(R.id.photo_num);
        find_num = findViewById(R.id.find_num);
        game_bg = findViewById(R.id.game_bg);
        photo_bg = findViewById(R.id.photo_bg);
        find_bg = findViewById(R.id.find_bg);

    }

    private void initDate() {
        views = new ArrayList<>();
        views.add(game_bg);
        views.add(photo_bg);
        views.add(find_bg);
        sticky.setHomeCallback(this);
        title_back.setOnClickListener(this);
        title_share.setOnClickListener(this);
        title_menu.setOnClickListener(this);

        game_ll.setOnClickListener(this);
        photo_ll.setOnClickListener(this);
        find_ll.setOnClickListener(this);

        fragments = new ArrayList<>();
        fragments.add(new GameFragment());
        fragments.add(new PhotoFragment());
        fragments.add(new FindFragment());
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

        };
        clear();
        show(0);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clear();
                show(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void clear() {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setVisibility(View.GONE);
        }
    }

    private void show(int position) {
        views.get(position).setVisibility(View.VISIBLE);
    }

    private void setCurrent(int position) {
        clear();
        show(position);
        mViewPager.setCurrentItem(position);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_share:
                break;
            case R.id.title_menu:
                break;
            case R.id.game_ll:
                setCurrent(0);
                break;
            case R.id.photo_ll:
                setCurrent(1);
                break;
            case R.id.find_ll:
                setCurrent(2);
                break;
        }
    }

    @Override
    public void startUp(int dy) {
        if (dy > (Utils.dip2px(context, 150))) {
            dy = (Utils.dip2px(context, 150));
        }
        float scale;
        if (dy != 0) {
            scale = (float) ((Utils.dip2px(context, 150)) - dy / 2) / (float) (Utils.dip2px(context, 150));
        } else {
            scale = 1;
        }
        ObjectAnimator.ofFloat(photo_img, "translationY", dy / 3 * 2).setDuration(0).start();
        ObjectAnimator.ofFloat(photo_img, "scaleX", scale).setDuration(0).start();
        ObjectAnimator.ofFloat(photo_img, "scaleY", scale).setDuration(0).start();

        float alpha;
        if (dy != 0) {
            alpha = (float) ((Utils.dip2px(context, 75)) - dy) / (float) (Utils.dip2px(context, 75));
        } else {
            alpha = 1;
        }
        ObjectAnimator.ofFloat(info_ll, "alpha", alpha).setDuration(0).start();
        ObjectAnimator.ofFloat(flower_ll, "alpha", alpha).setDuration(0).start();
    }
}
