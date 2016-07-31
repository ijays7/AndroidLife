package com.ijays.androidlife.mvptest;

/**
 * Created by ijaysdev on 16/7/31.
 */

public interface Presenter<V extends PictureView> {
    void attachView(V PictureView);

    void detachView();
}
