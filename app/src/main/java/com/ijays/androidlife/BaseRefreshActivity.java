package com.ijays.androidlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.Bind;

/**
 * Created by ijays on 2016/9/13.
 */

public abstract class BaseRefreshActivity extends BaseToolbarActivity {
    @Bind(R.id.refresh_layout)
    protected SwipeRefreshLayout mSwipeLayout;
    private boolean mRefreshStatus = false;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        if (mSwipeLayout != null) {
            mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
            mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onSwipeRefresh();
                }
            });
        }
    }

    protected abstract void onSwipeRefresh();

    public void setRefreshStatus(boolean refreshStatus) {
        this.mRefreshStatus = refreshStatus;
    }

    public boolean isRefreshing() {
        return mRefreshStatus;
    }


    public void refresh(boolean refresh) {
        if (mSwipeLayout == null) {
            return;
        }
        if (!refresh && mRefreshStatus) {
            mSwipeLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeLayout.setRefreshing(false);
                    mRefreshStatus = false;

                }
            }, 600);

        } else if (!mRefreshStatus) {
            mSwipeLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeLayout.setRefreshing(true);
                    mRefreshStatus = true;
                }
            });
        }
    }
}
