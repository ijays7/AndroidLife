package com.ijays.androidlife.web;

import com.ijays.androidlife.model.GankBeautyResult;
import com.ijays.androidlife.model.GankDaily;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ijays on 2016/7/22.
 */

public interface ApiService {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getWelfare(@Path("number") int number, @Path("page") int page);

    @GET("data/{type}/{number}/{page}")
    Observable<GankDaily> getData(@Path("type") String type,
                                  @Path("number") int number, @Path("page") int page);
}
