package com.byd.james.topspeedserver.utils;

import android.text.TextUtils;

import java.util.Random;

/**
 * Created by james on 2016/12/21.
 */

public class StringUtils {
    //获取随机的的字符串
    public static int getRandomNum(int min,int max)
    {
        return new Random().nextInt(max)%(max-min+1)+min;
    }

    /**
     * 根据Url获取catalogId
     *
     * @param url
     * @return
     */
    public static String getCatalogId(String url) {
        String catalogId = "";
        if (!TextUtils.isEmpty(url) && url.contains("="))
            catalogId = url.substring(url.lastIndexOf("=") + 1);
        return catalogId;
    }
}
