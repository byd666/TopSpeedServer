package com.byd.james.topspeedserver.model.net;

import com.byd.james.topspeedserver.model.bean.Vcode;
import com.byd.james.topspeedserver.model.bean.VideoRes;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by james on 2016/12/29.
 */

public interface HttpApis {
    //存储接口的接口
    String HOST="http://10.0.182.72/";//主体地址
    String VIDEO_HOST = "http://api.svipmovie.com/front/";//视频的主页地址


    @Headers( {"Content-Type:application/json; charset=UTF-8"})
    @POST(value = "sms")
    Observable<HttpResponse<Vcode>> getvCode(@Query("phone") String phone);


    /**
     * 首页
     *
     * @return
     */
    @GET("homePageApi/homePage.do")
    Observable<HttpResponse<VideoRes>> getHomePage();
    /**
     * 影片详情
     *
     * @param mediaId 影片id
     * @return
     */
    @GET("videoDetailApi/videoDetail.do")
    Observable<HttpResponse<VideoRes>> getVideoInfo(@Query("mediaId") String mediaId);

    /**
     * 影片分类列表
     *
     * @param catalogId
     * @param pnum
     * @return
     */
    @GET("columns/getVideoList.do")
    Observable<HttpResponse<VideoRes>> getVideoList(@Query("catalogId") String catalogId, @Query("pnum") String pnum);

}
