package com.byd.james.topspeedserver.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.app.App;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.presenter.MainContract;
import com.byd.james.topspeedserver.ui.fragment.BoutiqueFragment;
import com.byd.james.topspeedserver.ui.fragment.FindFragment;
import com.byd.james.topspeedserver.ui.fragment.MineFragment;
import com.byd.james.topspeedserver.ui.fragment.RecommendFragment;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.Pretreatment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, MainContract.View,NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.main_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.main_rgContent)
    RadioGroup rgContent;
    @BindView(R.id.sideSlip_menu_navigationView)
    NavigationView sideSlipMenuNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    private List<Fragment> fList;
    private int mIndex = 0;
    private View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder= ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //初始化控件
        setToolBar();
        initFragmentList();
        //设置默选中
        ((RadioButton) rgContent.getChildAt(0)).setChecked(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fList.get(0)).commit();
        rgContent.setOnCheckedChangeListener(this);
        //侧滑菜单的头布局
        headerView = sideSlipMenuNavigationView.getHeaderView(0);
        //获取头像
        headerView.findViewById(R.id.sideSlip_menu_headlayout_headImage);
        //获取用户名
        headerView.findViewById(R.id.sideSlip_menu_headlayout_headtext);
        sideSlipMenuNavigationView.setNavigationItemSelectedListener(this);
    }

    /*private void setFloatingActionBar() {
        //为floatingDraftButton添加需要管理的floatingActionButton
        mButton.registerButton(fMine);
        mButton.registerButton(fRecommend);
        mButton.registerButton(fFind);
        mButton.registerButton(fBoutique);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUtil.slideButtons(MainActivity.this,mButton);
            }
        });
    }*/

    private void initFragmentList() {
        //初始化fragment的集合
        fList = new ArrayList<>();
        BoutiqueFragment boutique = new BoutiqueFragment();
        RecommendFragment recommend = new RecommendFragment();
        FindFragment find = new FindFragment();
        MineFragment mine = new MineFragment();
        fList.add(boutique);
        fList.add(recommend);
        fList.add(find);
        fList.add(mine);
        //设置默认选中
    }

    private void setToolBar() {
        //标题栏的设置
        mToolBar.setTitle(getResources().getString(R.string.boutique));
        mToolBar.setLogo(R.mipmap.my_select);
    }

    //选择相应fragmnet
    private void switchFragment(int index) {
        FragmentManager manage = getSupportFragmentManager();
        //获取当前的fragment
        Fragment currentFragment = fList.get(mIndex);
        //展示的fragment
        Fragment showfragment = fList.get(index);
        //点击按钮控制fragment的显示和隐藏
        if (index != mIndex) {
            if (!showfragment.isAdded()) {//如没有添加事务，则添加，并展示，隐藏当前的fragment
                manage.beginTransaction().add(R.id.fragment_container, showfragment).hide(currentFragment).commit();
            } else {
                //添加了直接展示，并隐藏当前的fragment
                manage.beginTransaction().show(showfragment).hide(currentFragment).commit();
            }
        }
        mIndex = index;
    }


    @Override//RadioGroup的点击事件
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.tab_rb_1:
                switchFragment(0);
                mToolBar.setTitle(getResources().getString(R.string.boutique));
                break;
            case R.id.tab_rb_2:
                switchFragment(1);
                mToolBar.setTitle(getResources().getString(R.string.recommend));
                break;
            case R.id.tab_rb_3:
                switchFragment(2);
                mToolBar.setTitle(getResources().getString(R.string.find));
                break;
            case R.id.tab_rb_4:
                switchFragment(3);
                mToolBar.setTitle(getResources().getString(R.string.mine));
                break;
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        //预处理，判断presenter是否为空,不为空则返回
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override//展示相应的错误信息
    public void showError(String error) {
        EventUtil.showToast(this, error);
    }
    //侧滑菜单的点击监听
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId)
        {
            case R.id.sideSlip_menu_collect:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sideSlip_menu_download:
                Toast.makeText(this, "下载", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sideSlip_menu_welfare:
                Toast.makeText(this, "福利", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sideSlip_menu_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sideSlip_menu_suggest:
                Toast.makeText(this, "建议", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sideSlip_menu_setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sideSlip_menu_exit:
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                App.getInstance().exitApp();
                break;
            case R.id.sideSlip_menu_login:
                Toast.makeText(this, "登錄", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}
