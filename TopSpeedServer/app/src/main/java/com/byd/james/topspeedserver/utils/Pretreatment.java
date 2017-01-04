package com.byd.james.topspeedserver.utils;

import android.support.annotation.NonNull;

/**
 * Created by james on 2016/12/20.
 */

public final class Pretreatment {
    public static <T> T checkNotNull(T reference){
        if(reference==null)
        {
            //如果为空时抛出空指针异常
            throw new NullPointerException();
        }else{
            return reference;
        }
    }
    public static <T> T checkNotNull(T reference, @NonNull Object errorMessage){
        if(reference==null)
        {
            //如果为空时抛出带有错误信息的空指针异常
            throw new NullPointerException(String.valueOf(errorMessage));
        }else{
            return reference;
        }
    }
}
