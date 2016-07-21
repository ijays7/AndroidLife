package com.ijays.androidlife.utils;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

/**
 * Created by ijaysdev on 16/7/21.
 * 动画工具类
 */
public class AnimationUtil {
    public static final LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPLATOR = new LinearOutSlowInInterpolator();

    public static void scaleShow(View view, ViewPropertyAnimatorListener listener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(500)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPLATOR)
                .setListener(listener)
                .start();
    }

    public static void scaleHide(View view, ViewPropertyAnimatorListener listener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(500)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPLATOR)
                .setListener(listener)
                .start();
    }
}
