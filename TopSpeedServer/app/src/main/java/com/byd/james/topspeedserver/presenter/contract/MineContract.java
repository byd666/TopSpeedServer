package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;

/**
 * Created by james on 2017/1/4.
 */

public interface MineContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void showContent();

    }
    interface Presenter extends BasePresenter{
        void getNativeData();
        void deleteAllData();
    }
}
