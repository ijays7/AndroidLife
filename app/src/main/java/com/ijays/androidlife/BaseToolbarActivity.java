package com.ijays.androidlife;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;

/**
 * Created by ijaysdev on 16/7/31.
 */

public abstract class BaseToolbarActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppbarLayout;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (mToolbar == null || mAppbarLayout == null) {
            return;
        }
        setSupportActionBar(this.mToolbar);
    }

    protected void showBack() {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
