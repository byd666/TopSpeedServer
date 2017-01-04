package com.byd.james.topspeedserver.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by james on 2017/1/3.
 */

public abstract class RootView<T extends BasePresenter> extends LinearLayout{
    protected Context context;
    protected Unbinder unbinder;
    protected boolean mActive;//是否销毁
    protected T mPresenter;

    public RootView(Context context) {
        super(context);
        init();
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        //初始化
        context=getContext();
        getLayout();
        unbinder= ButterKnife.bind(this);
        mActive=true;
        initView();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void initView();

    protected abstract void getLayout();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(mPresenter!=null)
        {
            mPresenter.attachView(this);
        }
        mActive=true;

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mPresenter!=null)
        {
            mPresenter.detachView();
        }
        mPresenter=null;
        unbinder.unbind();
        mActive=false;
        context=null;
    }
}
