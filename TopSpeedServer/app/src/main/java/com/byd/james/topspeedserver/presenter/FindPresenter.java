package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.model.bean.VideoRes;
import com.byd.james.topspeedserver.model.net.HttpResponse;
import com.byd.james.topspeedserver.model.net.RetrofitHelper;
import com.byd.james.topspeedserver.presenter.contract.FindContract;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.RxUtils;
import com.byd.james.topspeedserver.utils.StringUtils;
import com.byd.james.topspeedserver.utils.SystemUtils;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by james on 2017/1/2.
 */

public class FindPresenter extends RxPresenter implements FindContract.Presenter{
    FindContract.View mView;
    public String catalogId="402834815584e463015584e53843000b";

    public FindPresenter(@NonNull FindContract.View mView) {
        this.mView = Pretreatment.checkNotNull(mView);
        this.mView.setPresenter(this);
    }

    @Override
    public void getData() {
        //获取数据
        getNextData();
    }

    private void getNextData() {
        //通过Rx的方式去获取数据
        Subscription subscription= RetrofitHelper.getVideoHttpApis().getVideoList(catalogId,getNextPage()+"")
                .compose(RxUtils.<HttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtils.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(VideoRes videoRes) {
                        if(mView.isActive()){
                            mView.showContent(videoRes);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //抛出错误
                        mView.showError(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        //关闭加载动画
                        if(mView.isActive())
                        {
                            mView.hideLoading();
                        }
                     }
                });
        addSubscribe(subscription);
    }

    private int getNextPage() {
        //获取下一页
        int page=mView.getLastPage();
        if(SystemUtils .isNetworkConnected()){
            page= StringUtils.getRandomNum(1,90);
            mView.setLastPage(page);
        }
        return page;
    }
}
