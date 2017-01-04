package com.byd.james.topspeedserver.presenter;

import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.presenter.contract.ShareMoodContract;
import com.byd.james.topspeedserver.utils.Pretreatment;

/**
 * Created by james on 2016/12/23.
 */

public class ShareMoodPresenter  implements ShareMoodContract.Presenter{
    ShareMoodContract.View mView;

    public ShareMoodPresenter(@NonNull ShareMoodContract.View view) {
        mView= Pretreatment.checkNotNull(view);
        mView.setPresenter(this);
    }
    @Override
    public void loadData() {

    }

    @Override
    public void attachView(Object view) {
        this.mView= (ShareMoodContract.View) view;
    }

    @Override
    public void detachView() {
        this.mView=null;
    }
}
