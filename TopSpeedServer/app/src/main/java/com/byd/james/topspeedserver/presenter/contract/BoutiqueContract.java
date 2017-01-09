package com.byd.james.topspeedserver.presenter.contract;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;
import com.byd.james.topspeedserver.model.bean.VideoRes;

/**
 * Created by james on 2017/1/4.
 */

public interface BoutiqueContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void showContent(VideoRes videoRes);
        void hindLoading();
    }
    interface Presenter extends BasePresenter{
        void getNetData();
    }
}
