package com.familydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.familydemo.R;
import com.familydemo.adapter.PhotoAdapter;


/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class PhotoFragment extends BaseFragment {

    private View view;
    private RecyclerView photo_recycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, null);
        initView();
        return view;
    }

    private void initView() {
        photo_recycle = view.findViewById(R.id.recycle);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = photo_recycle.getAdapter().getItemViewType(position);
                if (type == 2) {
                    return 2;
                }
                return 1;
            }
        });
        photo_recycle.setLayoutManager(manager);
        photo_recycle.setAdapter(new PhotoAdapter(getActivity()));
    }
}
