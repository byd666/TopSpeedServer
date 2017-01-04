package com.byd.james.topspeedserver.utils;

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
}
