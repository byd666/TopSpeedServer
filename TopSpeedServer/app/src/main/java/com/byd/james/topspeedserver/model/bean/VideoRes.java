package com.byd.james.topspeedserver.model.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by james on 2017/1/3.
 */

public class VideoRes {
    @SerializedName("list")
    public List<VideoType> list;
    public String title;
    public String score;
    public String videoType;
    public String region;
    public String airTime;
    public String director;
    public String actors;
    public String pic;
    public String description;
    public String smoothURL;
    public String SDURL;
    public String HDURL;

    public String getVideoURL() {
        if(!TextUtils.isEmpty(HDURL))
        {
            return HDURL;
        } else if(!TextUtils.isEmpty(SDURL))
        {
            return SDURL;
        }else if(!TextUtils.isEmpty(smoothURL))
        {
            return smoothURL;
        }
         return "";
    }
}
