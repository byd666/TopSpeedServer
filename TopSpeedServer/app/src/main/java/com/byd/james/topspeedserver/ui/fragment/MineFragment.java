package com.byd.james.topspeedserver.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseFragment;
import com.byd.james.topspeedserver.presenter.MinePresenter;
import com.byd.james.topspeedserver.ui.view.MineView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_view)
    MineView mineView;

    public MineFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mPresenter=new MinePresenter(mineView);
    }
}
