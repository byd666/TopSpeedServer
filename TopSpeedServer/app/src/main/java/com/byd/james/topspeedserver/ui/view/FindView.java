package com.byd.james.topspeedserver.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.app.Constants;
import com.byd.james.topspeedserver.base.RootView;
import com.byd.james.topspeedserver.model.bean.VideoRes;
import com.byd.james.topspeedserver.model.bean.VideoType;
import com.byd.james.topspeedserver.presenter.contract.FindContract;
import com.byd.james.topspeedserver.ui.adapter.SwipeDeckAdapter;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.PreUtils;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.ScreenUtil;
import com.byd.james.topspeedserver.widget.Loading;
import com.byd.james.topspeedserver.widget.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by james on 2017/1/2.
 */

public class FindView extends RootView<FindContract.Presenter> implements FindContract.View {

    @BindView(R.id.swipe_deck)
    SwipeDeck swipeDeck;
    @BindView(R.id.tv_noMore)
    TextView tvNoMore;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.swipe_frameLayout)
    SwipeFrameLayout swipeFrameLayout;
    @BindView(R.id.loading)
    Loading loading;
    private List<VideoType> mList = new ArrayList<>();
    private SwipeDeckAdapter mAdapter;

    public FindView(Context context) {
        super(context);
    }

    public FindView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(context, R.layout.fragment_find_view, this);
    }

    @Override
    protected void initView() {
        ViewGroup.LayoutParams lp = swipeDeck.getLayoutParams();
        lp.height = ScreenUtil.getContentHeight(getContext()) / 3 * 2;
        swipeDeck.setLayoutParams(lp);
        swipeDeck.setHardwareAccelerationEnabled(true);
    }

    @Override
    protected void initEvent() {
        //swipeDeck的回调
        swipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(VideoRes res) {
        if (res != null) {
            mList.clear();
            //将数据添加到集合
            mList.addAll(res.list);
            //清空swipeDeck
            swipeDeck.removeAllViews();
            mAdapter = new SwipeDeckAdapter(context, mList);
            swipeDeck.setAdapter(mAdapter);
            //没有更多可见
            tvNoMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        //数据加载完成后让动画消失
        loading.setVisibility(View.GONE);
    }

    @Override
    public void setLastPage(int page) {
        PreUtils.putInt(getContext(), Constants.DISCOVERlASTpAGE, page);
    }

    @Override
    public int getLastPage() {
        return PreUtils.getInt(getContext(), Constants.DISCOVERlASTpAGE, 1);
    }

    @Override
    public void setPresenter(FindContract.Presenter presenter) {
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(getContext(), error);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        //点击下一个，更换page
       tvNoMore.setVisibility(View.GONE);
       //加载动画
       loading.setVisibility(View.VISIBLE);
       mPresenter.getData();
    }
}
