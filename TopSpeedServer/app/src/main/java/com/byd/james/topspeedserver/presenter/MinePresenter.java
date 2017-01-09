package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.presenter.contract.MineContract;
import com.byd.james.topspeedserver.utils.Pretreatment;

/**
 * Created by james on 2017/1/4.
 */

public class MinePresenter extends RxPresenter implements MineContract.Presenter{
    MineContract.View mView;


    public MinePresenter(@NonNull MineContract.View mView) {
        this.mView = Pretreatment.checkNotNull(mView);
        this.mView.setPresenter(this);
        getNativeData();
    }


    @Override
    public void getNativeData() {
        //获取本地数据库中的历史数据

    }

    @Override
    public void deleteAllData() {
        //删除所有的数据
    }
}
