package com.ijays.androidlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.ijays.androidlife.widget.CircularAnimUtil;

import butterknife.Bind;

/**
 * Created by ijays on 2016/7/28.
 */

public class WebContentActivity extends BaseToolbarActivity {
    @Bind(R.id.web_view)
    WebView mWebView;

    public static void jumpToWebView(Context context, View view, String title, String url) {
        Intent intent = new Intent(context, WebContentActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        CircularAnimUtil.startActivity((Activity) context, intent, view, R.color.colorPrimary);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        mToolbar.setTitle(title);
        mWebView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
