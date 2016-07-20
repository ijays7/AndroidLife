package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import butterknife.Bind;

/**
 * Created by ijays on 2016/7/20.
 */
public abstract class BaseToolbarActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        initToolbarHelper();
    }

    protected void initToolbarHelper() {
        if (mToolbar == null || mAppBarLayout == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
