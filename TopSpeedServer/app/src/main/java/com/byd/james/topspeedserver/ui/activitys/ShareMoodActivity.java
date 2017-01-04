package com.byd.james.topspeedserver.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.presenter.ShareMoodPresenter;
import com.byd.james.topspeedserver.presenter.contract.ShareMoodContract;
import com.byd.james.topspeedserver.ui.adapter.ShowImageAdapter;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.JumpUtils;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.widget.DividerGridItemDecoration;
import com.byd.james.topspeedserver.widget.PoPupWindows;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.byd.james.topspeedserver.R.id.iv_collect;

public class ShareMoodActivity extends BaseActivity implements ShareMoodContract.View{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(iv_collect)
    TextView ivCollect;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.mood_et)
    EditText moodEt;
    @BindView(R.id.mood_recyclerView)
    RecyclerView moodRecyclerView;
    @BindView(R.id.addImage_iv)
    ImageView addImageIv;
    @BindView(R.id.addImage_tv)
    TextView addImageTv;
    @BindView(R.id.addImage_iv_right)
    ImageView addImageIvRight;
    @BindView(R.id.mood_add_image)
    RelativeLayout moodAddImage;
    @BindView(R.id.location_iv)
    ImageView locationIv;
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.location_iv_right)
    ImageView locationIvRight;
    @BindView(R.id.mood_add_location)
    RelativeLayout moodAddLocation;
    //请求码
    private final static int  REQUEST_CODE=111;
    private final static int REQUEST_CODE1=1000;
    private String mLocation;
    private PoPupWindows mPopup;
    private ArrayList<String> fList;

    private ShowImageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_mood);
        unbinder=ButterKnife.bind(this);
        mPresenter=new ShareMoodPresenter(this);

        initView();
        initEvent();
    }

    private void initView() {
        //设置RecyclerView
        fList=new ArrayList<>();
        moodRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        moodRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mAdapter=new ShowImageAdapter(this,fList);
        moodRecyclerView.setAdapter(mAdapter);


    }

    private void initEvent() {
        moodAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转
                //跳转到高德地图的界面并获取当前位置
                JumpUtils.jumpToGaoDeActivity(ShareMoodActivity.this,GaoDeMapActivity.class,REQUEST_CODE);
            }
        });
        moodAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出popupWindows
                mPopup=new PoPupWindows(ShareMoodActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopup.dismiss();
                        switch (v.getId())
                        {
                            case R.id.popup_window_from_phone:
                                Toast.makeText(ShareMoodActivity.this, "手机", Toast.LENGTH_SHORT).show();
                                JumpUtils.jumpToSelectImageActivity(ShareMoodActivity.this,SelectImageActivity.class,REQUEST_CODE1);
                                break;
                            case R.id.popup_window_from_net:
                                Toast.makeText(ShareMoodActivity.this, "网络", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.popup_window_camera:
                                Toast.makeText(ShareMoodActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                mPopup.showAtLocation(ShareMoodActivity.this
                        .findViewById(R.id.activity_share_mood),
                        Gravity.CENTER|Gravity.BOTTOM,0,0);
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleName.setText("写说说");
        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发表说说

            }
        });
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent() {
        //展示图片和文字

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==100 && data!=null)
        {
            mLocation= data.getStringExtra("location");

            //获取ui并更新
            if(mLocation!=null)
            {
                //将定位图片和右边的next取掉，并给textview赋值
                locationIv.setVisibility(View.GONE);
                locationIvRight.setVisibility(View.GONE);
                locationTv.setText(mLocation);
            }else if(data.getStringExtra("noChange").equals("NO")){
                //不改变布局
                locationIv.setVisibility(View.VISIBLE);
                locationIvRight.setVisibility(View.VISIBLE);
                locationTv.setText(R.string.mood_location);
            }
        }else if(requestCode==REQUEST_CODE1 && resultCode==101 && data!=null)
        {
            ArrayList<String> imageShow = data.getStringArrayListExtra("imageShow");
           // Log.e("AAAA", "selectClick2: "+imageShow.size()+imageShow.toString() );
            this.fList.addAll(imageShow);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(ShareMoodContract.Presenter presenter) {
        mPresenter= Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(this,error);
    }
}
