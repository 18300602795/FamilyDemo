package com.familydemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.fragment.BaseFragment;
import com.familydemo.fragment.HunterFragment;
import com.familydemo.fragment.MessageFragment;
import com.familydemo.fragment.PersonFragment;
import com.familydemo.fragment.TravelsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private List<BaseFragment> fragments;
    private HunterFragment hunterFragment;
    private TravelsFragment travelsFragment;
    private MessageFragment messageFragment;
    private PersonFragment personFragment;
    private FragmentManager fm;
    private int current;

    private LinearLayout item_ll1;
    private LinearLayout item_ll2;
    private LinearLayout item_ll3;
    private LinearLayout item_ll4;
    private LinearLayout item_ll5;

    private ImageView item_img1;
    private ImageView item_img2;
    private ImageView item_img3;
    private ImageView item_img4;
    private ImageView item_img5;

    private TextView item_tv1;
    private TextView item_tv2;
    private TextView item_tv4;
    private TextView item_tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        fragments = new ArrayList<>();
        fm = getFragmentManager();
        initView();
        showFragment(1);
    }

    private void initView() {
        item_ll1 = findViewById(R.id.item_ll1);
        item_ll2 = findViewById(R.id.item_ll2);
        item_ll3 = findViewById(R.id.item_ll3);
        item_ll4 = findViewById(R.id.item_ll4);
        item_ll5 = findViewById(R.id.item_ll5);

        item_img1 = findViewById(R.id.item_img1);
        item_img2 = findViewById(R.id.item_img2);
        item_img3 = findViewById(R.id.item_img3);
        item_img4 = findViewById(R.id.item_img4);
        item_img5 = findViewById(R.id.item_img5);

        item_tv1 = findViewById(R.id.item_tv1);
        item_tv2 = findViewById(R.id.item_tv2);
        item_tv4 = findViewById(R.id.item_tv4);
        item_tv5 = findViewById(R.id.item_tv5);

        item_ll1.setOnClickListener(this);
        item_ll2.setOnClickListener(this);
        item_ll3.setOnClickListener(this);
        item_ll4.setOnClickListener(this);
        item_ll5.setOnClickListener(this);
    }

    public void showFragment(int type) {
        FragmentTransaction transaction = fm.beginTransaction();
        hideAll(transaction);
        if (current != 0)
            setDefault(current);
        switch (type) {
            case 1:
                if (hunterFragment == null) {
                    hunterFragment = new HunterFragment();
                    transaction.add(R.id.frameLayout, hunterFragment);
                    fragments.add(hunterFragment);
                }
                current = 1;
                item_tv1.setTextColor(getResources().getColor(R.color.red));
                item_img1.setImageResource(R.mipmap.ic_tab_dynamic_press);
                transaction.show(hunterFragment);
                break;
            case 2:
                if (travelsFragment == null) {
                    travelsFragment = new TravelsFragment();
                    transaction.add(R.id.frameLayout, travelsFragment);
                    fragments.add(travelsFragment);
                }
                current = 2;
                item_tv2.setTextColor(getResources().getColor(R.color.red));
                item_img2.setImageResource(R.mipmap.tab_discover_down);
                transaction.show(travelsFragment);
                break;
            case 3:

                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.frameLayout, messageFragment);
                    fragments.add(messageFragment);
                }
                current = 3;
                item_tv4.setTextColor(getResources().getColor(R.color.red));
                item_img4.setImageResource(R.mipmap.guide_im_down);
                transaction.show(messageFragment);
                break;
            case 4:
                if (personFragment == null) {
                    personFragment = new PersonFragment();
                    transaction.add(R.id.frameLayout, personFragment);
                    fragments.add(personFragment);
                }
                current = 4;
                item_tv5.setTextColor(getResources().getColor(R.color.red));
                item_img5.setImageResource(R.mipmap.tab_mine_selected);
                transaction.show(personFragment);
                break;
        }
        transaction.commit();
    }

    private void setDefault(int type) {
        switch (type) {
            case 1:
                item_tv1.setTextColor(getResources().getColor(R.color.tv_bg));
                item_img1.setImageResource(R.mipmap.ic_tab_dynamic_normal);
                break;
            case 2:
                item_tv2.setTextColor(getResources().getColor(R.color.tv_bg));
                item_img2.setImageResource(R.mipmap.tab_discover_up);
                break;
            case 3:
                item_tv4.setTextColor(getResources().getColor(R.color.tv_bg));
                item_img4.setImageResource(R.mipmap.guide_im_up);
                break;
            case 4:
                item_tv5.setTextColor(getResources().getColor(R.color.tv_bg));
                item_img5.setImageResource(R.mipmap.tab_mine);
                break;
        }
    }

    private void hideAll(FragmentTransaction transcation) {
        for (BaseFragment fragment : fragments) {
            transcation.hide(fragment);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_ll1:
                showFragment(1);
                break;
            case R.id.item_ll2:
                showFragment(2);
                break;
            case R.id.item_ll3:
                break;
            case R.id.item_ll4:
                showFragment(3);
                break;
            case R.id.item_ll5:
                showFragment(4);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (personFragment != null)
            personFragment.onActivityResult(requestCode, resultCode, data);
    }
}
