package com.byd.james.topspeedserver.utils;

import android.text.TextUtils;

import com.byd.james.topspeedserver.model.net.ApiException;
import com.byd.james.topspeedserver.model.net.HttpResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by james on 2016/12/21.
 */

public class RxUtils {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<HttpResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<HttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResponse<T> hResponse) {
                        if (hResponse.getCode() == 200) {
                            return createData(hResponse.getRet());
                        } else if (!TextUtils.isEmpty(hResponse.getMessage())) {
                            return Observable.error(new ApiException("*" + hResponse.getMessage()));
                        } else {
                            return Observable.error(new ApiException("*" + "服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
