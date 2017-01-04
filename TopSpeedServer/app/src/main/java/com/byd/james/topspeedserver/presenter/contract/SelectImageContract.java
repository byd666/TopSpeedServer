package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;
import com.byd.james.topspeedserver.model.bean.ImageBean;

import java.util.List;

/**
 * Created by james on 2016/12/26.
 */

public interface SelectImageContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();
        void showContent(List<ImageBean> list);
    }

    interface Presenter extends BasePresenter {
        //从获取数据
        void loadData();
    }
}
