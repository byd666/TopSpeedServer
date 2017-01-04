package com.byd.james.topspeedserver.model.net;

import com.byd.james.topspeedserver.BuildConfig;
import com.byd.james.topspeedserver.app.Constants;
import com.byd.james.topspeedserver.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by james on 2016/12/29.
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient=null;
    private static HttpApis httpApis;
    public static HttpApis getHttpApis()
    {
        initOkHttpClient();
        //进行网络请求
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(HttpApis.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
            httpApis=retrofit.create(HttpApis.class);
        return httpApis;
    }
    public static HttpApis getVideoHttpApis()
    {
        //获取视频的数据，使用Gson解析工厂，返回对象
        initOkHttpClient();
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(HttpApis.VIDEO_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        httpApis=retrofit.create(HttpApis.class);
        return httpApis;
    }

    private static void initOkHttpClient() {
        //初始化
        if(okHttpClient==null)
        {
          OkHttpClient.Builder  builder=new OkHttpClient.Builder();
            if(BuildConfig.DEBUG)
            {
               //来编写只在Debug模式下运行的代码,拦截信息
                HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(interceptor);
            }
            File cacheFile = new File(Constants.PATH_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    //判断网络是否连接，无网络时缓存
                    if (!SystemUtils.isNetworkConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    //尝试连接的数量
                    int tryCount = 0;
                    Response response = chain.proceed(request);
                    while (!response.isSuccessful() && tryCount < 3) {

                        tryCount++;

                        // retry the request重新请求
                        response = chain.proceed(request);
                    }
                    if (SystemUtils.isNetworkConnected()) {
                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }
}
