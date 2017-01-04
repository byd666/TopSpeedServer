package com.byd.james.topspeedserver.ui.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.model.bean.ImageBean;
import com.byd.james.topspeedserver.presenter.SelectImagePresenter;
import com.byd.james.topspeedserver.presenter.contract.SelectImageContract;
import com.byd.james.topspeedserver.ui.adapter.SelectIamgeAdapter;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.JumpUtils;
import com.byd.james.topspeedserver.utils.Pretreatment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectImageActivity extends BaseActivity implements SelectImageContract.View {

    @BindView(R.id.select_image_recyclerView)
    RecyclerView selectImageRecyclerView;
    private ProgressDialog mDialog;
    private List<ImageBean> mImageList;
    private SelectIamgeAdapter mAdapter;

    private ArrayList<String> imagePathList;
    private final static int RequestCode = 110;

    private void addData2View() {
        //将数据展示在控件上
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        initView();
        unbinder = ButterKnife.bind(this);
        mPresenter = new SelectImagePresenter(mDialog, this, this);
        selectImageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectImageRecyclerView.setAdapter(mAdapter = new SelectIamgeAdapter(mImageList, this));
        imagePathList = new ArrayList<>();

        initEvent();
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new SelectIamgeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArrayList<String> list, String fileName) {
                JumpUtils.jumpToSelectImage2Activity(SelectImageActivity.this, SelectImage2Activity.class, list, fileName, RequestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode && resultCode == 112 && data != null) {
            imagePathList = data.getStringArrayListExtra("imageShow");
            if (imagePathList.size() > 0 && imagePathList != null) {
                Log.e("bbbbb", "onActivityResult: " + imagePathList.size() + imagePathList.toString());
                this.setResult(101, data);
            }
            finish();
        }
    }

    private void initView() {
        mImageList = new ArrayList<>();
        mDialog = new ProgressDialog(this);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<ImageBean> list) {
        this.mImageList = list;

    }

    @Override
    public void setPresenter(SelectImageContract.Presenter presenter) {
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(this, error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
