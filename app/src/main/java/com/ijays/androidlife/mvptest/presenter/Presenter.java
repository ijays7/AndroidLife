package com.ijays.androidlife.mvptest.presenter;

import com.ijays.androidlife.mvptest.view.PictureView;

/**
 * Created by ijaysdev on 16/7/31.
 */

public interface Presenter<V extends PictureView> {
    void attachView(V PictureView);

    void detachView();
}
