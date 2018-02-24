package com.familydemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.familydemo.R;
import com.familydemo.adapter.ViewPagerAdapter;
import com.familydemo.utils.DimensionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class HunterHeaderView extends LinearLayout {
    View mRootView;
    private Context mContext;
    private ViewPager hunter_pager;
    private LinearLayout ll_dots;
    private ViewPagerAdapter pagerAdapter;
    private List<Integer> imgs;
    private List<View> mDots = new ArrayList<>();// 存放圆点视图的集合

    private ImageView img_notice;
    private AnimationDrawable animationDrawable;
    private TextSwitcher tv_notice;
    private String[] mAdvertisements;
    private final int HOME_AD_RESULT = 1;
    private int mSwitcherCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    mSwitcherCount++;
                    String str = mAdvertisements[mSwitcherCount % mAdvertisements.length];
                    String[] strings = str.split(" ");
                    String name = strings[0];
                    int bstart = str.indexOf(name);
                    int bend = bstart + name.length();
                    SpannableStringBuilder style = new SpannableStringBuilder(str);
                    style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), bstart, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    tv_notice.setText(style);
                    mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
                    break;
            }

        }
    };

    public HunterHeaderView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public HunterHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public HunterHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.hunter_header, this);
        hunter_pager = mRootView.findViewById(R.id.hunter_pager);
        ll_dots = mRootView.findViewById(R.id.ll_dots);
        tv_notice = (TextSwitcher) mRootView.findViewById(R.id.tv_notice);
        tv_notice.setFactory(new ViewSwitcher.ViewFactory() {
            // 这里用来创建内部的视图，这里创建TextView，用来显示文字
            public View makeView() {
                TextView tv = new TextView(mContext);
                // 设置文字的显示单位以及文字的大小
                return tv;
            }
        });
        tv_notice.setInAnimation(mContext,
                R.anim.enter_bottom);
        tv_notice.setOutAnimation(mContext, R.anim.leave_top);
        mAdvertisements = new String[]{"aa 收到了游戏上分邀约", "bb 收到了游戏辅导邀约", "cc 收到了游戏上分邀约"};
        mHandler.sendEmptyMessage(HOME_AD_RESULT);
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
            pagerAdapter = new ViewPagerAdapter(imgs, mContext);
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
            View dot = new View(mContext);
            int size = DimensionUtil.dip2px(mContext, 8);
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

}
