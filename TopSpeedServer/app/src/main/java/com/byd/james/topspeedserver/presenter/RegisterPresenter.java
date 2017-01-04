package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.model.bean.Vcode;
import com.byd.james.topspeedserver.model.net.HttpResponse;
import com.byd.james.topspeedserver.model.net.RetrofitHelper;
import com.byd.james.topspeedserver.presenter.contract.RegisterContract;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.RxUtils;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by james on 2016/12/29.
 */

public class RegisterPresenter extends RxPresenter implements RegisterContract.Presenter{
    RegisterContract.View mView;

    public RegisterPresenter(@NonNull RegisterContract.View mView) {
        this.mView = Pretreatment.checkNotNull(mView);
        this.mView.setPresenter(this);
    }

    @Override
    public void onRefresh() {
       //下载数据，获取message ok

        Subscription rxSubscription= RetrofitHelper.getHttpApis().getvCode(mView.getPhone())
                .compose(RxUtils.<HttpResponse<Vcode>>rxSchedulerHelper())
                .compose(RxUtils.<Vcode>handleResult())
                .subscribe(new Action1<Vcode>() {
                    @Override
                    public void call(Vcode vcode) {
                        //获取vcode
                        mView.showContent(vcode);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("验证码获取失败！");
                    }
                });
         addSubscribe(rxSubscription);
    }

}
