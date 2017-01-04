package com.byd.james.topspeedserver.ui.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.ui.adapter.AdFragmentAdapter;
import com.byd.james.topspeedserver.ui.fragment.AdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.activity_splash_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.activity_splash_rg)
    RadioGroup radioGroup;
    private List<Fragment> fList;
    private AdFragmentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        initView();
        initEvent();
    }


    private void initView() {
        fList=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            AdFragment fragment=new AdFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("id",i);
            fragment.setArguments(bundle);
            fList.add(fragment);
        }
        mAdapter=new AdFragmentAdapter(getSupportFragmentManager(),fList);
        mViewpager.setAdapter(mAdapter);

    }

    private void initEvent() {
        //設置默認選中
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        //对viewpager监听
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)radioGroup.getChildAt(position)).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
