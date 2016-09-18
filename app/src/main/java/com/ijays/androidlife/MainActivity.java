package com.ijays.androidlife;


import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ijays.androidlife.adapter.GankAdapter;


import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.container)
    View mContainer;


    private boolean initialize = false;
    private GankAdapter mAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mBottomSheetBehavior = BottomSheetBehavior.from(mContainer);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!initialize) {
            initialize = true;
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
