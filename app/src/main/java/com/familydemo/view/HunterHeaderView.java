package com.familydemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.familydemo.R;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class HunterHeaderView extends RelativeLayout {
    View mRootView;
    private Context mContext;

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
    }
}
