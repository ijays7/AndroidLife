package com.ijays.androidlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by ijaysdev on 16/7/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        initToolbar(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initListener();
    }

    /**
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * initialize views
     *
     * @param savedInstanceState
     */
    protected void initViews(Bundle savedInstanceState) {
    }

    /**
     * @param savedInstanceState
     */
    protected void initToolbar(Bundle savedInstanceState) {
    }

    /**
     *
     */
    protected void initData() {
    }

    /**
     *
     */
    protected void initListener() {
    }

    /**
     * find view by id
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
