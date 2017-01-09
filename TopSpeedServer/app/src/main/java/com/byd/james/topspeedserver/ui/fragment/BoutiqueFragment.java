package com.byd.james.topspeedserver.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseFragment;
import com.byd.james.topspeedserver.presenter.BoutiquePresenter;
import com.byd.james.topspeedserver.ui.view.BoutiqueView;

import butterknife.BindView;

/**
 * A simple {@link Fragment}精品模块
 */
public class BoutiqueFragment extends BaseFragment {

    @BindView(R.id.boutique_view)
    BoutiqueView boutiqueView;
    public BoutiqueFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_boutique;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mPresenter=new BoutiquePresenter(boutiqueView);
    }

    @Override//懒加载数据
    protected void layFetchData() {
        //调用presenter层的方法去下载数据
        ((BoutiquePresenter)mPresenter).getNetData();
    }
}
