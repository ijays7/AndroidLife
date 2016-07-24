package com.ijays.androidlife.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ijaysdev on 16/7/24.
 */

public class BaseGankData {
    //            "_id": "56cc6d26421aa95caa707f68",
//            "createdAt": "2015-12-04T13:34:24.420Z",
//            "desc": "滑动 Toolbar 展开 View",
//            "publishedAt": "2016-07-08T11:58:38.92Z",
//            "type": "Android",
//            "url": "https://github.com/NikoYuwono/ToolbarPanel",
//            "used": true,
//            "who": "mthli
// "
    @SerializedName("createdAt")
    public String createdAt;
    //标题
    @SerializedName("desc")
    public String desc;
    @SerializedName("publishedAt")
    public Date publishedAt;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("who")
    public String who;
    @SerializedName("_id")
    public String _id;
    //是否使用
    @SerializedName("used")
    public boolean used;
}
