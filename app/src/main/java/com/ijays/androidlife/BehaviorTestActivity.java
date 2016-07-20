package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ijays on 2016/7/20.
 */
public class BehaviorTestActivity extends BaseToolbarActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.container)
    View mContainer;


    private boolean initialize = false;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected int getLayoutId() {
        return R.layout.behavior_test_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        ListAdapter adapter = new ListAdapter(list);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        mBottomSheetBehavior = BottomSheetBehavior.from(mContainer);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
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
