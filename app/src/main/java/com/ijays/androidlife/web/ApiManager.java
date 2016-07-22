package com.ijays.androidlife.web;

import com.ijays.androidlife.AppConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ijays on 2016/7/22.
 */

public class ApiManager {
    private static ApiService mApiService;
    private static GsonConverterFactory mGsonConverterFactory = GsonConverterFactory.create();
    private static RxJavaCallAdapterFactory mRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ApiService getWelfare() {
        if (mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstant.GANK_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(mGsonConverterFactory)
                    .addCallAdapterFactory(mRxJavaCallAdapterFactory)
                    .build();
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
