package com.familydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class CommentHeaderView extends RelativeLayout {
    View mRootView;
    private Context mContext;
    private ImageAdapter adapter;
    private List<Integer> imgs;
    private RecyclerView img_recycle;
    private TextView like_num;

    public CommentHeaderView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CommentHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public CommentHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.comment_header, this);
        img_recycle = mRootView.findViewById(R.id.img_recycle);
        like_num = mRootView.findViewById(R.id.like_num);
        img_recycle.setLayoutManager(new LinearLayoutManager(mContext));
        imgs = new ArrayList<>();
        imgs.add(R.mipmap.item1);
        imgs.add(R.mipmap.item2);
        imgs.add(R.mipmap.item4);
        imgs.add(R.mipmap.item3);
        adapter = new ImageAdapter(mContext, imgs);
        img_recycle.setAdapter(adapter);

        ImageSpan imgSpan = new ImageSpan(mContext, R.mipmap.praise_1215);
        SpannableString spanString = new SpannableString("icon  ");
        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        like_num.setText(spanString);
        like_num.append("aaa, bbb, sada, daw, dasd, das, das, ad, dasd, sdad, aaa, bbb, sada, daw, dasd, das, das, ad, dasd, sdad等21人觉得很赞");
    }
}
