package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.ijays.androidlife.adapter.ListAdapter;
import com.ijays.androidlife.model.GankBeautyResult;
import com.ijays.androidlife.model.GankModel;
import com.ijays.androidlife.web.ApiManager;
import com.ijays.androidlife.widget.ScaleDownShowBehavior;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    private ListAdapter mAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;
    private Observer<List<String>> observer = new Observer<List<String>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<String> strings) {

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.behavior_test_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggerManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add("我是第" + i + "个");
//        }
        loadGankImg();
        mAdapter = new ListAdapter(this, list);
        mRecyclerView.setLayoutManager(staggerManager);
        mRecyclerView.setAdapter(mAdapter);


        mBottomSheetBehavior = BottomSheetBehavior.from(mContainer);
        ScaleDownShowBehavior scaleDownShowBehavior = ScaleDownShowBehavior.from(mFab);
        scaleDownShowBehavior.setOnStateChangedListener(this);
    }

    private void loadGankImg() {
        ApiManager.getWelfare()
                .getWelfare(20, 1)
                .map(new Func1<GankBeautyResult, List<String>>() {
                    @Override
                    public List<String> call(GankBeautyResult gankBeautyResult) {
                        List<String> imgList = new ArrayList<>();
                        for (GankModel model : gankBeautyResult.gankResults) {
                            imgList.add(model.url);
                        }
                        return imgList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mAdapter.setData(strings);
                    }
                });
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
