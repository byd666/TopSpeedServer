package com.byd.james.topspeedserver.model.net;

import rx.Subscriber;

/**
 * Created by james on 2016/12/29.
 */

public abstract class MyObserver <T> extends Subscriber<T>{
    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, 123));
        }
    }
    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
