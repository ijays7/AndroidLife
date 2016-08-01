package com.ijays.androidlife.mvptest.view;

/**
 * Created by ijaysdev on 16/7/31.
 */

public interface PictureView {
    void onError(Throwable e);

    /**
     * 下载成功
     * @param path
     */
    void onDownloadSuccess(String path);
}
