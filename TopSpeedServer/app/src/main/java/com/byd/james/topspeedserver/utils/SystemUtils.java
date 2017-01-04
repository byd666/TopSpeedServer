package com.byd.james.topspeedserver.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.byd.james.topspeedserver.app.App;

/**
 * Created by james on 2016/12/29.
 */

public class SystemUtils {
    //获取系统的一些信息
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
