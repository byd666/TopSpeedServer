package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.presenter.contract.WelcomeContract;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by james on 2016/12/21.
 */

public class WelcomePresenter extends RxPresenter implements WelcomeContract.Presenter{
    WelcomeContract.View mView;

    public WelcomePresenter(@NonNull WelcomeContract.View view) {
       mView= Pretreatment.checkNotNull(view);
       mView.setPresenter(this);
       getWelcomeData();
    }

    @Override//获取本地文件中的数据
    public void getWelcomeData() {
        mView.showContent(getImage());
        start();
    }

    private void start() {
        //开始加载
        Subscription rxSubscription= rx.Observable.timer(3000, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())//线程调度
                .subscribe(new Action1<Long>() {//订阅
                    @Override
                    public void call(Long aLong) {
                        mView.jump2MainActivity();//3秒后跳到主界面
                    }
                });
        //将事件添加到集合中
        addSubscribe(rxSubscription);
    }

    private List<String> getImage() {
        //从Assets文件中获取图片
        List<String> imgs=new ArrayList<>();
        imgs.add("file:///android_asset/a.jpg");
        imgs.add("file:///android_asset/b.jpg");
        imgs.add("file:///android_asset/c.jpg");
        imgs.add("file:///android_asset/d.jpg");
        imgs.add("file:///android_asset/e.jpg");
        imgs.add("file:///android_asset/f.jpg");
        imgs.add("file:///android_asset/g.jpg");
        return imgs;
    }
}
