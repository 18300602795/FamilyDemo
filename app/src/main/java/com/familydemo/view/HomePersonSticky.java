package com.familydemo.view;

import android.content.Context;
import android.util.AttributeSet;

import com.familydemo.utils.Utils;

/**
 * Created by Administrator on 2018\2\28 0028.
 */

public class HomePersonSticky extends StickyNavLayout{
    public HomePersonSticky(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTop.getMeasuredHeight() - Utils.dip2px(getContext(), 150);
    }
}
