package com.ijays.androidlife.mvptest.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.ijays.androidlife.BaseToolbarActivity;
import com.ijays.androidlife.R;
import com.ijays.androidlife.mvptest.presenter.PicturePresenter;

import butterknife.BindView;


/**
 * 图片详情页
 * Created by ijaysdev on 16/7/31.
 */

public class PictureActivity extends BaseToolbarActivity implements PictureView, View.OnLongClickListener {
    @BindView(R.id.iv_picture)
    ImageView mPictureIV;

    private String mTitle;
    private String mUrl;
    private GlideBitmapDrawable mGlideBitmapDrawable;

    private PicturePresenter mPresenter;


    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_layout;
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadSuccess(String path) {
        Toast.makeText(this, "the path is " + path, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        showBack();
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mUrl = intent.getStringExtra("url");
        setTitle(mTitle);
        Glide.with(this)
                .load(mUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mGlideBitmapDrawable = (GlideBitmapDrawable) resource;

                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(mPictureIV)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (!mPictureIV.isShown()) {
                            mPictureIV.setVisibility(View.VISIBLE);
                        }
                    }
                });
        mPresenter = new PicturePresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void initListener() {
        mPictureIV.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.iv_picture:
                new AlertDialog.Builder(this)
                        .setMessage("下载到手机")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                download();
                                dialog.dismiss();
                            }
                        }).show();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.download_pic) {
            download();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void download() {
        if (mGlideBitmapDrawable != null) {
            mPresenter.downloadPicture(this, mGlideBitmapDrawable, getApplication());
        }
    }

    @Override
    protected void onDestroy() {
        if (mGlideBitmapDrawable != null) {
            mGlideBitmapDrawable.setCallback(null);
            mGlideBitmapDrawable = null;
        }
        mPresenter.detachView();
        super.onDestroy();
    }
}
