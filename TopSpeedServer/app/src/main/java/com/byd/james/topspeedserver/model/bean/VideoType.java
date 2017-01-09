package com.byd.james.topspeedserver.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by james on 2017/1/3.
 */

public class VideoType {
    public String title;
    public String moreURL;
    public String pic;
    public String dataId;
    public String airTime;
    public String score;
    public String description;
    public String msg;
    public String phoneNumber;
    public String userPic;
    public String time;
    public String showType;
    public String likeNum;
    @SerializedName("childList")
    public List<VideoInfo> childList;

}
