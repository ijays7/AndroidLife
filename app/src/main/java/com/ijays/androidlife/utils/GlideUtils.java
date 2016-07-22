package com.ijays.androidlife.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ijays.androidlife.R;

/**
 * Created by ijaysdev on 16/7/22.
 */
public class GlideUtils {
    private static final String TAG = "GlideUtils";

    public static void display(ImageView view, String url) {
        displayUrl(view, url, R.mipmap.img_default_gray);
    }

    public static void displayUrl(ImageView view, String url, int defalutImage) {
        if (view == null) {
            return;
        }

        Context context = view.getContext();
        //判断view是否存活
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defalutImage)
                .crossFade()
                .centerCrop()
                .into(view);

    }

}
