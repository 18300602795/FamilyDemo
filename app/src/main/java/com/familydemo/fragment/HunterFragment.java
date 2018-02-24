package com.familydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.familydemo.R;
import com.familydemo.adapter.HunterAdapter;
import com.familydemo.view.HunterHeaderView;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class HunterFragment extends BaseFragment {

    private View view;
    private HunterHeaderView headerView;
    private ListView hunter_list;
    private HunterAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hunter, null);
        hunter_list = view.findViewById(R.id.hunter_list);
        headerView = new HunterHeaderView(getActivity());
        adapter = new HunterAdapter(getActivity());
        hunter_list.addHeaderView(headerView);
        hunter_list.setAdapter(adapter);
        return view;
    }


}
