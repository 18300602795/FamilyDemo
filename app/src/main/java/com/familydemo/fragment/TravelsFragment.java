package com.familydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.ForumAdapter;


/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class TravelsFragment extends BaseFragment {

    private View view;
    private RecyclerView recyclerView;
    private ForumAdapter adapter;

    private ImageView title_back;
    private TextView title_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travels, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        title_back = view.findViewById(R.id.title_back);
        title_tv = view.findViewById(R.id.title_tv);
        title_back.setVisibility(View.INVISIBLE);
        title_tv.setText("游记");
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ForumAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }
}
