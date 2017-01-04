package com.byd.james.topspeedserver.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by james on 2016/12/28.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment{
    protected Context mContext;
    protected View rootView;
    protected Unbinder unbinder;
    protected T mPresenter;
    private boolean hasFetchData;//已经加载过数据
    private boolean isPreparedView;//fragment视图是否初始化完毕

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity!=null)
        {
            this.mContext=activity;
        }else{
            this.mContext=getActivity();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null)
        {
            rootView=inflater.inflate(getLayout(),container,false);
        }
        ViewGroup parent= (ViewGroup) rootView.getParent();
        if(parent!=null)
        {
            parent.removeView(rootView);
        }
        unbinder= ButterKnife.bind(this,rootView);
        initView(inflater);
        EventBus.getDefault().register(this);
        setTitleHeight();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化监听事件
        initEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPreparedView=true;//视图初始化完毕
        //加载数据
        layDownLoadData();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        //销毁视图，
        isPreparedView=false;
        hasFetchData=false;
        mPresenter=null;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null)
        {
            //销毁注入工具
            unbinder.unbind();
        }
    }

    @Override//用户是否可见
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            //若用户可见，预加载数据
            layDownLoadData();
        }
    }

    private void layDownLoadData() {
        if(getUserVisibleHint() && isPreparedView && !hasFetchData)
        {
            //fragment用户可见，视图加载完成，并且还没有加载数据
            layFetchData();
        }


    }

    protected void layFetchData() {
        //加载数据
        /**
         * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次*/
    }

    protected abstract int getLayout() ;
    //设置主题的方法
    public void  setTheme(String Tag){

    }
    private void setTitleHeight() {
    }

    protected void initView(LayoutInflater inflater) {
    }

    protected void initEvent() {
    }


}
