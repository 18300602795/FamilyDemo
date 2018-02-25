package com.familydemo.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;

/**
 * Created by Administrator on 2018\2\25 0025.
 */

public class TakePhotoDialog extends Dialog implements View.OnClickListener {
    private LinearLayout camera_ll, album_ll;
    private TextView title_tv;
    private ImageView close_iv;
    private Context mContext;
    private TakePhotoCallBack photoCallBack;

    public void setPhotoCallBack(TakePhotoCallBack photoCallBack) {
        this.photoCallBack = photoCallBack;
    }

    public TakePhotoDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public TakePhotoDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected TakePhotoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo);
        camera_ll = findViewById(R.id.camera_ll);
        album_ll = findViewById(R.id.album_ll);
        close_iv = findViewById(R.id.close_iv);
        title_tv = findViewById(R.id.title_tv);
        camera_ll.setOnClickListener(this);
        album_ll.setOnClickListener(this);
        close_iv.setOnClickListener(this);
        initView();
    }

    private void initView() {
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void cancel() {
        camera_ll.clearAnimation();
        album_ll.clearAnimation();
        final Animation camera_animation = AnimationUtils.loadAnimation(mContext, R.anim.take_photo_out);
        final Animation album_animation = AnimationUtils.loadAnimation(mContext, R.anim.take_photo_out);
        final Animation title_animation = AnimationUtils.loadAnimation(mContext, R.anim.dialog_out);
        final Animation close_animation = AnimationUtils.loadAnimation(mContext, R.anim.dialog_out);
        camera_animation.setFillAfter(true);
        album_animation.setFillAfter(true);
        album_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        camera_ll.setAnimation(camera_animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                album_ll.setAnimation(album_animation);
            }
        }, 50);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                title_tv.setAnimation(title_animation);
                close_iv.setAnimation(close_animation);
            }
        }, 100);
    }

    public void showAnim() {
        final Animation camera_animation = AnimationUtils.loadAnimation(mContext, R.anim.take_photo_in);
        final Animation album_animation = AnimationUtils.loadAnimation(mContext, R.anim.take_photo_in);
        camera_animation.setFillAfter(true);
        album_animation.setFillAfter(true);
        camera_ll.setAnimation(camera_animation);
        album_ll.setVisibility(View.INVISIBLE);
        Handler hanler = new Handler();
        hanler.postDelayed(new Runnable() {
            @Override
            public void run() {
                album_ll.setVisibility(View.VISIBLE);
                album_ll.setAnimation(album_animation);
            }
        }, 50);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_ll:
                if (photoCallBack != null)
                    photoCallBack.takePhoto();
                break;
            case R.id.album_ll:
                if (photoCallBack != null)
                    photoCallBack.takeAlbum();
                break;
            case R.id.close_iv:
                if (photoCallBack != null)
                    photoCallBack.close();
                break;
        }
    }
}
