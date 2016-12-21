package com.byd.james.topspeedserver.presenter;

import com.byd.james.topspeedserver.base.BasePresenter;
import com.byd.james.topspeedserver.base.BaseView;

/**
 * Created by james on 2016/12/20.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{}

    interface Presenter extends BasePresenter {}
}
