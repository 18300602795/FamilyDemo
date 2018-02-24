package com.familydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.familydemo.R;
import com.familydemo.activity.FriendActivity;
import com.familydemo.activity.VideoActivity;
import com.familydemo.view.CircleImageView;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class PersonFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private CircleImageView photo_img;
    private LinearLayout video_ll;
    private LinearLayout photos_ll;

    private LinearLayout msg_ll, circle_ll, gift_ll, friends_ll, fav_ll, upload_ll, real_ll, setting_ll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, null);
        initView(view);
        photo_img.setOnClickListener(this);
        video_ll.setOnClickListener(this);
        photos_ll.setOnClickListener(this);

        msg_ll.setOnClickListener(this);
        circle_ll.setOnClickListener(this);
        gift_ll.setOnClickListener(this);
        friends_ll.setOnClickListener(this);
        fav_ll.setOnClickListener(this);
        upload_ll.setOnClickListener(this);
        real_ll.setOnClickListener(this);
        setting_ll.setOnClickListener(this);
        return view;
    }

    private void initView(View view) {
        photo_img = view.findViewById(R.id.photo_img);
        video_ll = view.findViewById(R.id.video_ll);
        photos_ll = view.findViewById(R.id.photos_ll);

        msg_ll = view.findViewById(R.id.msg_ll);
        circle_ll = view.findViewById(R.id.circle_ll);
        gift_ll = view.findViewById(R.id.gift_ll);
        friends_ll = view.findViewById(R.id.friends_ll);
        fav_ll = view.findViewById(R.id.fav_ll);
        upload_ll = view.findViewById(R.id.upload_ll);
        real_ll = view.findViewById(R.id.real_ll);
        setting_ll = view.findViewById(R.id.setting_ll);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_img:
                break;
            case R.id.video_ll:
                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
            case R.id.photos_ll:
                break;
            case R.id.msg_ll:
                break;
            case R.id.circle_ll:
                break;
            case R.id.gift_ll:
                break;
            case R.id.friends_ll:
                startActivity(new Intent(getActivity(), FriendActivity.class));
                break;
            case R.id.fav_ll:
                break;
            case R.id.upload_ll:
                break;
            case R.id.real_ll:
                break;
            case R.id.setting_ll:
                break;
        }
    }
}
