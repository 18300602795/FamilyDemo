package com.familydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.familydemo.R;
import com.familydemo.adapter.FindAdapter;


/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class FindFragment extends BaseFragment {

    private View view;
    private RecyclerView find_recycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, null);
        initView();
        return view;
    }

    private void initView() {
        find_recycle = view .findViewById(R.id.recycle);
        find_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        find_recycle.setAdapter(new FindAdapter(getActivity()));
    }
}
