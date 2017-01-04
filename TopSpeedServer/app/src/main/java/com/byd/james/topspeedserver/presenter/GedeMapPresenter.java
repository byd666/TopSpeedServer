package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.presenter.contract.GeDeMapContract;
import com.byd.james.topspeedserver.utils.Pretreatment;

/**
 * Created by james on 2016/12/25.
 */

public class GedeMapPresenter  implements GeDeMapContract.Presenter{
    GeDeMapContract.View mView;

    public GedeMapPresenter(@NonNull GeDeMapContract.View mView) {
        this.mView= Pretreatment.checkNotNull(mView);
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void attachView(Object view) {
        this.mView= (GeDeMapContract.View) view;
    }

    @Override
    public void detachView() {
        this.mView=null;
    }
}
