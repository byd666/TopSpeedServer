package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;
import com.byd.james.topspeedserver.model.bean.VideoRes;

/**
 * Created by james on 2017/1/2.
 */

public interface FindContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        //展示数据
        void showContent(VideoRes res);
        //隐藏加载动画
        void hideLoading();
        //设置下一页
        void setLastPage(int page);
        //获得下一页
        int getLastPage();


    }
    interface Presenter extends BasePresenter{
        void getData();
    }
}
