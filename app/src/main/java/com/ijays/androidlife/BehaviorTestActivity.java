package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ijays.androidlife.adapter.BorderDividerItemDecoration;
import com.ijays.androidlife.adapter.GankAdapter;
import com.ijays.androidlife.adapter.ListAdapter;
import com.ijays.androidlife.model.BaseGankData;
import com.ijays.androidlife.model.GankBeautyResult;
import com.ijays.androidlife.model.GankDaily;
import com.ijays.androidlife.model.GankModel;
import com.ijays.androidlife.web.ApiManager;
import com.ijays.androidlife.widget.CircularAnimUtil;
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
public class BehaviorTestActivity extends BaseRefreshActivity implements ScaleDownShowBehavior.OnStateChangedListener,
        View.OnClickListener {

    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.container)
    View mContainer;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private int mPage = 1;
    private boolean initialize = false;
    private boolean mIsLoadMore;
    private ListAdapter mAdapter;
    private GankAdapter mGankAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;
    private BorderDividerItemDecoration mItemDecoration;

    @Override
    protected int getLayoutId() {
        return R.layout.behavior_test_layout;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        super.initToolbar(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggerManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        this.mItemDecoration = new BorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        List<String> list = new ArrayList<>();
        List<BaseGankData> gankDataList = new ArrayList<>();

//        loadGankImg();
//        loadGankData();
        mAdapter = new ListAdapter(this, list);
//        mGankAdapter=new GankAdapter(this,gankDataList);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setLayoutManager(staggerManager);
//        mRecyclerView.setAdapter(mAdapter);


        mBottomSheetBehavior = BottomSheetBehavior.from(mContainer);
        ScaleDownShowBehavior scaleDownShowBehavior = ScaleDownShowBehavior.from(mFab);
        scaleDownShowBehavior.setOnStateChangedListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mGankAdapter = new GankAdapter(BehaviorTestActivity.this, new ArrayList<BaseGankData>());
        mRecyclerView.setAdapter(mGankAdapter);
        loadGankData();
    }

    private void loadGankData() {
        ApiManager.getInstance()
                .getApiService()
                .getData(AppConstant.DATA_TYPE_ALL, 20, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankDaily>() {
                    @Override
                    public void onCompleted() {
                        if (isRefreshing()) {
                            refresh(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isRefreshing()) {
                            refresh(false);
                        }
                    }

                    @Override
                    public void onNext(GankDaily gankDaily) {

                        if (mIsLoadMore) {
                            mGankAdapter.addDataList(gankDaily.results);
                        } else {
                            mGankAdapter.setDataList(gankDaily.results);
                        }


                    }
                });
    }

    private void loadGankImg() {
        ApiManager.getInstance()
                .getApiService()
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
                CircularAnimUtil.startActivity(BehaviorTestActivity.this, MainActivity.class, view, R.color.colorPrimary);
                break;
            default:
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    protected void onSwipeRefresh() {
        refresh(true);
        mIsLoadMore=false;
        mPage = 1;
        loadGankData();
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
            lastVisibleItem = lm.findLastVisibleItemPosition();
        }
    }

    /**
     * 上拉加载更多
     */
    private void onLoadMore() {
        mPage++;
        mIsLoadMore = true;
        loadGankData();
    }
}
