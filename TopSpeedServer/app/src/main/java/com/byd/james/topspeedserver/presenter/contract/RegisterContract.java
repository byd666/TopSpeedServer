package com.byd.james.topspeedserver.presenter.contract;

import android.content.Context;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;
import com.byd.james.topspeedserver.model.bean.Vcode;

/**
 * Created by james on 2016/12/29.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();
        Context getContext();
        String getPhone();
        void showContent(Vcode vcode);
    }

    interface Presenter extends BasePresenter {
        //从网络获取数据
        void  onRefresh();
    }
}
