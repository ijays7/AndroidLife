package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ijays.androidlife.widget.ScaleDownShowBehavior;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ijays on 2016/7/20.
 */
public class BehaviorTestActivity extends BaseToolbarActivity implements ScaleDownShowBehavior.OnStateChangedListener,
        View.OnClickListener {

    @Bind(R.id.fab)
    FloatingActionButton mFab;
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
        ScaleDownShowBehavior scaleDownShowBehavior = ScaleDownShowBehavior.from(mFab);
        scaleDownShowBehavior.setOnStateChangedListener(this);
    }

    @Override
    protected void initListener() {
        mFab.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!initialize) {
            initialize = true;
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @Override
    public void onChanged(boolean isShow) {
        mBottomSheetBehavior.setState(isShow ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                break;
            default:
                break;
        }
    }
}
