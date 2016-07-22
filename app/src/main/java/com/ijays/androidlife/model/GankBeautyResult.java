package com.ijays.androidlife.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ijays on 2016/7/22.
 */

public class GankBeautyResult {
    @SerializedName("error")
    public boolean error;
    @SerializedName("results")
    public List<GankModel> gankResults;
}
