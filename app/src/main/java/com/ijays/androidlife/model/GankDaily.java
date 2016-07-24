package com.ijays.androidlife.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ijaysdev on 16/7/10.
 */
public class GankDaily implements Serializable {
    //    category": [
//            "iOS",
//            "Android",
//            "\u778e\u63a8\u8350",
//            "\u62d3\u5c55\u8d44\u6e90",
//            "\u798f\u5229",
//            "\u4f11\u606f\u89c6\u9891"
//            ],
//            "error": false,
//            "results": {
//        "Android": [
//        {
//            "_id": "56cc6d23421aa95caa707a69",
//                "createdAt": "2015-08-06T07:15:52.65Z",
//                "desc": "\u7c7b\u4f3cLink Bubble\u7684\u60ac\u6d6e\u5f0f\u64cd\u4f5c\u8bbe\u8ba1",
//                "publishedAt": "2015-08-07T03:57:48.45Z",
//                "type": "Android",
//                "url": "https://github.com/recruit-lifestyle/FloatingView",
//                "used": true,
//                "who": "mthli"
//        },
    @SerializedName("error")
    public boolean error;
    @SerializedName("results")
    public List<BaseGankData> results;
//    @SerializedName("category")
//    public List<String> category;

    public class DailyResults {
        @SerializedName("Android")
        public List<BaseGankData> androidData;
        @SerializedName("iOS")
        public List<BaseGankData> iosData;
        @SerializedName("APP")
        public List<BaseGankData> appData;
        @SerializedName("休息视频")
        public List<BaseGankData> restData;
        @SerializedName("前端")
        public List<BaseGankData> jsData;
        @SerializedName("拓展资源")
        public List<BaseGankData> resourceData;
        @SerializedName("瞎推荐")
        public List<BaseGankData> recommendData;
        @SerializedName("福利")
        public List<BaseGankData> welfareData;
    }
}
