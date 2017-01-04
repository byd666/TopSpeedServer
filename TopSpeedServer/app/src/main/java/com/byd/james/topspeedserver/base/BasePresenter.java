package com.byd.james.topspeedserver.base;

/**
 * Created by james on 2016/12/19.
 */

public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
