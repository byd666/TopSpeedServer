package com.byd.james.topspeedserver.ui.fragment;

import android.view.LayoutInflater;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseFragment;
import com.byd.james.topspeedserver.presenter.FindPresenter;
import com.byd.james.topspeedserver.ui.view.FindView;

import butterknife.BindView;
/*发现模块*/
public class FindFragment extends BaseFragment {
    @BindView(R.id.fragment_find_findView)
    FindView findView;

    public FindFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        //初始化Presenter
        mPresenter=new FindPresenter(findView);
    }

    @Override//为了下一次加载数据
    protected void layFetchData() {
        ((FindPresenter)mPresenter).getData();
    }
}
