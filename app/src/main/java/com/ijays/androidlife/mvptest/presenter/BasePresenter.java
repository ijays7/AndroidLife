package com.ijays.androidlife.mvptest.presenter;

import com.ijays.androidlife.mvptest.view.PictureView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ijaysdev on 16/7/31.
 */

public class BasePresenter<T extends PictureView> implements Presenter<T> {
    private T mPictureView;
    public CompositeSubscription mCompositeSubscription;


    @Override
    public void attachView(T PictureView) {
        this.mPictureView = PictureView;
        this.mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void detachView() {
        this.mPictureView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }

    public T getPictureView() {
        return mPictureView;
    }

    public boolean isViewAttached() {
        return mPictureView != null;
    }
}
