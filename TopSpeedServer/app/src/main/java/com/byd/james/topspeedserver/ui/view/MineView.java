package com.byd.james.topspeedserver.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.byd.james.topspeedserver.base.RootView;
import com.byd.james.topspeedserver.presenter.contract.MineContract;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.Pretreatment;

/**
 * Created by james on 2017/1/4.
 */

public class MineView extends RootView<MineContract.Presenter> implements MineContract.View{


    public MineView(Context context) {
        super(context);
    }

    public MineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void initEvent() {

    }

    @Override
    protected void initView() {

    }
    //向RecylerView上展示从本地获取的数据
    @Override
    public void showContent() {

    }
    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter= Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(getContext(),error);
    }
    @Override
    protected void getLayout() {
        //获取mineview的布局
    }

    @Override
    public boolean isActive() {
        //记录fragment是否销毁
        return mActive;
    }
}
