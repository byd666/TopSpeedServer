package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.model.bean.VideoRes;
import com.byd.james.topspeedserver.model.net.HttpResponse;
import com.byd.james.topspeedserver.model.net.RetrofitHelper;
import com.byd.james.topspeedserver.presenter.contract.BoutiqueContract;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.RxUtils;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by james on 2017/1/4.
 */

public class BoutiquePresenter extends RxPresenter implements BoutiqueContract.Presenter {
    BoutiqueContract.View mView;

    public BoutiquePresenter(@NonNull BoutiqueContract.View mView) {
        this.mView = Pretreatment.checkNotNull(mView);
        this.mView.setPresenter(this);

    }

    @Override
    public void getNetData() {
        //从网络中获取数据
        Subscription rxsubscription= RetrofitHelper.getVideoHttpApis().getHomePage()
                .compose(RxUtils.<HttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtils.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(VideoRes videoRes) {
                        //获取带有数据的对象
                        if (mView.isActive()) {
                            mView.showContent(videoRes);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取返回的错误信息
                        mView.showError(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        if(mView.isActive())
                        {
                            mView.hindLoading();
                        }
                    }
                });
        addSubscribe(rxsubscription);
    }





}
