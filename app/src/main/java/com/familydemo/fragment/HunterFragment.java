package com.familydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.familydemo.R;
import com.familydemo.adapter.HunterAdapter;
import com.familydemo.view.HunterHeaderView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class HunterFragment extends BaseFragment {

    private View view;
    private HunterHeaderView headerView;
    private RecyclerView hunter_recycle;
    private HunterAdapter adapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hunter, null);
        hunter_recycle = view.findViewById(R.id.hunter_recycle);
        hunter_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        headerView = new HunterHeaderView(getActivity());
        adapter = new HunterAdapter(getActivity());
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(headerView);
        mLoadMoreWrapper = new LoadMoreWrapper(headerAndFooterWrapper);
//        headerFooterAdapter.addHeaderView();
        hunter_recycle.setAdapter(mLoadMoreWrapper);
        return view;
    }


}
