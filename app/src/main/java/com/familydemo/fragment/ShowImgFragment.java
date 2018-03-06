package com.familydemo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.familydemo.R;
import com.familydemo.utils.PhotoUtils;

/**
 * Created by Administrator on 2018\3\1 0001.
 */

public class ShowImgFragment extends BaseFragment {
    private View view;
    private ImageView show_img;
    private ProgressBar progressBar;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    show_img.setImageBitmap((Bitmap) msg.obj);
                    if (isVisible) {
                        progressBar.setVisibility(View.GONE);
                        show_img.setImageBitmap((Bitmap) msg.obj);
                    } else {
                        showImg((Bitmap) msg.obj);
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_show_img, null);
        show_img = view.findViewById(R.id.show_img);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        show_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }

    public void showImg(Bitmap bitmap){
        Message message = new Message();
        message.obj = bitmap;
        message.what = 1;
        handler.sendMessage(message);
    }


    public void showImg(final String img) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = PhotoUtils.getimage(img, 1920, 1080);
                Message message = new Message();
                message.obj = bitmap;
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

}
