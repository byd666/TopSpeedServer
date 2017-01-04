package com.byd.james.topspeedserver.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.ui.adapter.SelectImage2Adapter;
import com.byd.james.topspeedserver.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectImage2Activity extends AppCompatActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.iv_collect)
    TextView ivCollect;
    @BindView(R.id.select_image_recyclerView2)
    RecyclerView mRecyclerView;
    @BindView(R.id.select_image_allAdd)
    TextView selectImageAllAdd;
    @BindView(R.id.select_image_submit)
    Button selectImageSubmit;
    //装有图片地址的集合
    private List<String> aList;
    private SelectImage2Adapter mAdapter;
    private ArrayList<String> pathList;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image2);
        ButterKnife.bind(this);
        aList = getIntent().getStringArrayListExtra("list");
        filePath=getIntent().getStringExtra("filePath");
        initView();
    }

    private void initView() {
        ivCollect.setText("取消");
        pathList=new ArrayList<>();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setAdapter(mAdapter=new SelectImage2Adapter(aList,this,filePath));
        mAdapter.setOnImageClickListener(new SelectImage2Adapter.OnImageClickListener() {
            @Override
            public void onImageItemClickListener(ArrayList<String> mImagePath) {
                Log.d(mImagePath.toString(), "onImageItemClickListener: ");
                pathList.addAll(mImagePath);
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.iv_collect, R.id.select_image_allAdd, R.id.select_image_submit})
    public void selectClick2(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                //退出当前activity，回到SelectImageActivity
                aList.clear();
                finish();
                break;
            case R.id.iv_collect:
                //退出当前和上一层activity并通过向下移动的动画回到sharemoodActivity,

                break;
            case R.id.select_image_allAdd:
                //全部选定
                break;
            case R.id.select_image_submit:
                //提交选定结果
                //携带数据跳转到ShareMoodActivity
                Log.e("AAAA", "selectClick2: "+pathList.size()+pathList.toString() );
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("imageShow",pathList);
                intent.putExtras(bundle);

                if(intent!=null&& pathList!=null && pathList.size()>0) {
                    this.setResult(112, intent);
                   // Log.e("AAAA", "selectClick2: " + pathList.size() + pathList.toString());
                }
                //JumpUtils.jump2ShareMoodActivity(SelectImage2Activity.this,ShareMoodActivity.class,pathList);
                finish();
                break;
        }
    }


}
