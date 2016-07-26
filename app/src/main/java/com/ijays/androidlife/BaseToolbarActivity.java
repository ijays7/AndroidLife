package com.ijays.androidlife;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import butterknife.Bind;

/**
 * Created by ijays on 2016/7/20.
 */
public abstract class BaseToolbarActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    protected ActionBarHelper mActionBarHelper;


    /**
     * Initialize the toolbar in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        this.initToolbarHelper();
    }


    /**
     * init the toolbar
     */
    protected void initToolbarHelper() {
        if (this.mToolbar == null || this.mAppBarLayout == null) {
            return;
        }

        this.setSupportActionBar(this.mToolbar);
//        mToolbar.setTitleTextColor(Color.WHITE);

        this.mActionBarHelper = this.createActionBarHelper();
        this.mActionBarHelper.init();
    }


    /**
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    protected void showBack() {
        if (this.mActionBarHelper != null) {
            this.mActionBarHelper.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * set the AppBarLayout alpha
     *
     * @param alpha alpha
     */
    protected void csetAppBarLayoutAlpha(float alpha) {
        this.mAppBarLayout.setAlpha(alpha);
    }


    /**
     * set the AppBarLayout visibility
     *
     * @param visibility visibility
     */
    protected void setAppBarLayoutVisibility(boolean visibility) {
        if (visibility) {
            this.mAppBarLayout.setVisibility(View.VISIBLE);
        } else {
            this.mAppBarLayout.setVisibility(View.GONE);
        }
    }


    /**
     * Create a compatible helper that will manipulate the action bar if available.
     */
    public ActionBarHelper createActionBarHelper() {
        return new ActionBarHelper();
    }


    public class ActionBarHelper {
        private final ActionBar mActionBar;
        public CharSequence mDrawerTitle;
        public CharSequence mTitle;


        public ActionBarHelper() {
            this.mActionBar = getSupportActionBar();
        }


        public void init() {
            if (this.mActionBar == null) return;
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
            this.mActionBar.setDisplayShowHomeEnabled(false);
            this.mTitle = mDrawerTitle = getTitle();
            Log.e("SONGJIE", "executed here");
        }


        public void onDrawerClosed() {
            if (this.mActionBar == null) return;
            this.mActionBar.setTitle(this.mTitle);
        }


        public void onDrawerOpened() {
            if (this.mActionBar == null) {
                return;
            }
            this.mActionBar.setTitle(this.mDrawerTitle);
        }


        public void setTitle(CharSequence title) {
            this.mTitle = title;
        }


        public void setDrawerTitle(CharSequence drawerTitle) {
            this.mDrawerTitle = drawerTitle;
        }


        public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
            if (this.mActionBar == null) {
                return;
            }
            this.mActionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }
}
