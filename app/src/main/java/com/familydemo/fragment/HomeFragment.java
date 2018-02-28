package com.familydemo.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.ViewPagerAdapter;
import com.familydemo.callback.HomeCallback;
import com.familydemo.utils.ColorUtil;
import com.familydemo.utils.DimensionUtil;
import com.familydemo.utils.Utils;
import com.familydemo.view.HomeHunterSticky;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\2\28 0028.
 */

public class HomeFragment extends BaseFragment implements HomeCallback {
    private View view;
    private ViewPager mViewPager;
    private List<BaseFragment> fragments;
    private FragmentPagerAdapter mAdapter;
    private HomeHunterSticky sticky;

    private LinearLayout title_bar;
    private ImageView title_search, title_menu;
    private TextView title_tv;


    private ViewPager hunter_pager;
    private LinearLayout ll_dots;
    private ViewPagerAdapter pagerAdapter;
    private List<Integer> imgs;
    private List<View> mDots = new ArrayList<>();// 存放圆点视图的集合

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        initDate();
        return view;
    }

    private void initView(View view) {
        mViewPager = view.findViewById(R.id.id_stickynavlayout_viewpager);
        hunter_pager = view.findViewById(R.id.hunter_pager);
        ll_dots = view.findViewById(R.id.ll_dots);
        sticky = view.findViewById(R.id.sticky);

        title_bar = view.findViewById(R.id.title_bar);
        title_search = view.findViewById(R.id.title_search);
        title_menu = view.findViewById(R.id.title_menu);
        title_tv = view.findViewById(R.id.title_tv);
    }

    private void initDate() {
        sticky.setHomeCallback(this);
        fragments = new ArrayList<>();
        fragments.add(new HunterFragment());
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        imgs = new ArrayList<>();
        imgs.add(R.mipmap.item1);
        imgs.add(R.mipmap.item2);
        imgs.add(R.mipmap.item3);
        hunter_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = mDots.size();
                for (int i = 0; i < count; i++) {
                    if (position % imgs.size() == i) {
                        mDots.get(i).setBackgroundResource(
                                R.mipmap.dot_focus);
                    } else {
                        mDots.get(i).setBackgroundResource(
                                R.mipmap.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                abc.removeCallbacksAndMessages(null);
                abc.sendEmptyMessageDelayed(0, 4000);
            }
        });
        showBanner();
        startScrollView();
    }


    Handler abc = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = hunter_pager.getCurrentItem();
            hunter_pager.setCurrentItem(currentItem + 1);
        }
    };


    /**
     * 开始轮播图片
     */
    private void startScrollView() {
        if (pagerAdapter == null) {
            pagerAdapter = new ViewPagerAdapter(imgs, getActivity());
            hunter_pager.setAdapter(pagerAdapter);
            hunter_pager.setCurrentItem(10000 * imgs.size());
        } else {
            pagerAdapter.notifyDataSetChanged();
        }
        // 实现轮播效果
        abc.sendEmptyMessageDelayed(0, 4000);
    }

    /**
     * 显示广告条
     */
    private void showBanner() {
        // 创建ViewPager对应的点
        ll_dots.removeAllViews();
        mDots.clear();
        for (int i = 0; i < imgs.size(); i++) {
            View dot = new View(getActivity());
            int size = DimensionUtil.dip2px(getActivity(), 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    size, size);
            params.leftMargin = size;
            if (i == 0) {
                dot.setBackgroundResource(R.mipmap.dot_focus);// 默认选择第1个点
            } else {
                dot.setBackgroundResource(R.mipmap.dot_normal);
            }
            dot.setLayoutParams(params);
            ll_dots.addView(dot);
            mDots.add(dot);
        }
    }

    @Override
    public void startUp(int dy) {
        if (dy > (Utils.dip2px(getActivity(), 90))) {
            dy = (Utils.dip2px(getActivity(), 90));
        }
        float alpha;
        if (dy != 0) {
            alpha = (float) dy / (float) (Utils.dip2px(getActivity(), 90));
        } else {
            alpha = 0;
        }
        Log.i("333", "alpha：" + alpha);
        title_bar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(getActivity(), alpha, R.color.transparent, R.color.white));
        ObjectAnimator.ofFloat(title_tv, "alpha", alpha).setDuration(0).start();
        if (alpha > 0.5){
            title_menu.setImageResource(R.drawable.icon_home_page_more_dark);
        }else {
            title_menu.setImageResource(R.drawable.icon_home_page_more);
        }
    }
}
