package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;

/**
 * Created by james on 2016/12/25.
 */

public interface GeDeMapContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        //从获取数据
        void loadData();
    }
}
