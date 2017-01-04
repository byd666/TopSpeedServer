package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;

/**
 * Created by james on 2016/12/23.
 */

public interface ShareMoodContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void showContent();
    }
    interface Presenter extends BasePresenter{
        //接收从selectImage2Activity传过来的地址的集合
        void loadData();
    }


}
