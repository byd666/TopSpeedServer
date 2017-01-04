package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;

import java.util.List;

/**
 * Created by james on 2016/12/21.
 */

public interface WelcomeContract {
    interface  View extends BaseView<Presenter>{
        //判断是否被销毁
        boolean isActive();
        //展示数据
        void showContent(List<String> list);
        //跳转到主界面的方法
        void jump2MainActivity();
    }

    interface Presenter extends BasePresenter{
        void getWelcomeData();
    }
}
