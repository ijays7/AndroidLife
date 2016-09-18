package com.ijays.androidlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ijays.androidlife.widget.CircularAnimUtil;

import butterknife.BindView;

/**
 * Created by ijays on 2016/7/28.
 */

public class WebContentActivity extends BaseToolbarActivity {
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.web_view_pb)
    ProgressBar mProgressBar;

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
        this.setTitle(title);
        showBack();
        mWebView.loadUrl(url);
    }

    @Override
    protected void initData() {
        super.initData();

        initWebView();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                mProgressBar.setProgress(newProgress);

                if (newProgress >= 80) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initWebView() {
        //JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        //
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        //
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportZoom(true);

        //enable caching
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            this.mWebView.destroy();
        }
        super.onDestroy();
    }
}
