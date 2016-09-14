package com.ijays.androidlife.mvptest.presenter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.ijays.androidlife.R;
import com.ijays.androidlife.mvptest.view.PictureView;
import com.ijays.androidlife.utils.DeviceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ijaysdev on 16/7/31.
 */

public class PicturePresenter extends BasePresenter<PictureView> {

    public rx.Observable<String> getSavedPictureObservable(
            final Context context, final GlideBitmapDrawable glideBitmapDrawable,
            final Application application) {

        return rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String dirPath = DeviceUtils.createAPPFolder(context.getString(R.string.app_name), application);
                    File downloadFile = new File(new File(dirPath),
                            UUID.randomUUID().toString().replace("-", "") + ".jpg");
                    if (!downloadFile.exists()) {
                        File parent = downloadFile.getParentFile();
                        if (parent != null && !parent.exists()) {
                            parent.mkdirs();
                        }
                    }


                    FileOutputStream outputStream = new FileOutputStream(downloadFile);
                    //100表示最高质量
                    glideBitmapDrawable.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    //
                    Uri uri = Uri.fromFile(downloadFile);
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                    context.sendBroadcast(intent);
                    subscriber.onNext(downloadFile.getPath());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).compose(new Observable.Transformer<String, String>() {
            @Override
            public Observable<String> call(Observable<String> stringObservable) {
                return stringObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        });

    }

    public void downloadPicture(Context context, GlideBitmapDrawable glideBitmapDrawable,
                                Application application) {
        mCompositeSubscription.add(
                getSavedPictureObservable(context, glideBitmapDrawable, application).subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                mCompositeSubscription.remove(this);
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (PicturePresenter.this.getPictureView() != null) {
                                    getPictureView().onError(e);
                                }
                            }

                            @Override
                            public void onNext(String s) {
                                if (PicturePresenter.this.getPictureView() != null) {
                                    PicturePresenter.this.getPictureView().onDownloadSuccess(s);
                                }
                            }
                        }
                )
        );
    }
}
