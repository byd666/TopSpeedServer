package com.byd.james.topspeedserver.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.RootView;
import com.byd.james.topspeedserver.model.bean.VideoInfo;
import com.byd.james.topspeedserver.model.bean.VideoRes;
import com.byd.james.topspeedserver.model.bean.VideoType;
import com.byd.james.topspeedserver.presenter.contract.BoutiqueContract;
import com.byd.james.topspeedserver.ui.adapter.RecyclerViewAdapter;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.widget.DividerGridItemDecoration;
import com.byd.james.topspeedserver.widget.Loading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 2017/1/4.
 */

public class BoutiqueView extends RootView<BoutiqueContract.Presenter> implements BoutiqueContract.View ,AdapterView.OnItemClickListener {
    View headerView;

    ViewPager mViewpager;
    TextView fragmentBoutiqueHeaderTv;
    RadioGroup mRg;

    @BindView(R.id.fragment_boutique_loading)
    Loading loading;
    @BindView(R.id.fragment_boutique_recyclerView)
    RecyclerView mRecyclerView;

    private List<VideoInfo> mChildList;
    private List<VideoType> dataList;
    private RecyclerViewAdapter mAdapter;

    public BoutiqueView(Context context) {
        super(context);
    }

    public BoutiqueView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(context, R.layout.fragment_boutique_layout, this);
    }


    @Override
    protected void initView() {
        headerView = LayoutInflater.from(context).inflate(R.layout.fragment_boutique_headview, null);
        mViewpager= ButterKnife.findById(headerView,R.id.fragment_boutique_header_viewpager);
        fragmentBoutiqueHeaderTv= ButterKnife.findById(headerView,R.id.fragment_boutique_header_tv);
        mRg = ButterKnife.findById(headerView,R.id.fragment_boutique_header_rg);

        loading=new Loading(getContext());
        mChildList=new ArrayList<>();
        dataList=new ArrayList<>();
        //添加recyclerview的基本设置
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));

    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void showContent(VideoRes videoRes) {
        if(videoRes!=null)
        {
            List<VideoType> list = videoRes.list;
            //头布局的数据
            VideoType videoType0 = list.get(0);
            mChildList= videoType0.childList;

            for(int i=1;i<list.size();i++)
            {
                //将数据从1-size截出来放入集合中，主体布局的数据
                dataList.add(list.get(i));
            }

            mAdapter=new RecyclerViewAdapter(getContext(),dataList);
            mRecyclerView.setAdapter(mAdapter);

        }
    }


    @Override
    public void hindLoading() {
        //数据加载完毕后隐藏加载动画
        loading.stopAnimation();
        loading.setVisibility(View.GONE);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }


    @Override
    public void setPresenter(BoutiqueContract.Presenter presenter) {
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(getContext(), error);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
