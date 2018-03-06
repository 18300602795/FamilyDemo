package com.familydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.activity.PostedActivity;
import com.familydemo.adapter.ForumAdapter;

import java.util.ArrayList;


/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class TravelsFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private RecyclerView recyclerView;
    private ForumAdapter adapter;
    private FloatingActionButton fab;
    private ArrayList<Integer> imgs;

    private ImageView title_back;
    private TextView title_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travels, null);
        initView(view);
        initDate();
        return view;
    }

    private void initView(View view) {
        title_back = view.findViewById(R.id.title_back);
        title_tv = view.findViewById(R.id.title_tv);
        title_back.setVisibility(View.INVISIBLE);
        fab = view.findViewById(R.id.fab);

    }
    private void initDate() {
        imgs = new ArrayList<>();
        imgs.add(R.mipmap.item1);
        imgs.add(R.mipmap.item2);
        imgs.add(R.mipmap.item3);
        title_tv.setText("游记");
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ForumAdapter(getActivity(), imgs);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(getActivity(), PostedActivity.class));
                break;
        }
    }
}
