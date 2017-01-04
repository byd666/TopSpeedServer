package com.byd.james.topspeedserver.presenter;

import com.byd.james.topspeedserver.base.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by james on 2016/12/21.
 *
 */
//基于Rx的presenter的分装，控制订阅的生命周期
public class RxPresenter<T> implements BasePresenter<T>{
    protected T mView;
    //订阅的封装类
    protected CompositeSubscription compositeSubscription;
    /*取消订阅的方法*/
    protected void UnSubscribe()
    {
        if(compositeSubscription!=null)
        {
            compositeSubscription.unsubscribe();
        }
    }
    protected void addSubscribe(Subscription subscription)
    {
        //添加订阅的接口，最多4个
        if(compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }else{
            compositeSubscription.add(subscription);
        }

    }
    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    @Override
    public void detachView() {
        this.mView=null;
        compositeSubscription.unsubscribe();
    }
}
