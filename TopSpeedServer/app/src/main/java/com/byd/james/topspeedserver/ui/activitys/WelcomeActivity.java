package com.byd.james.topspeedserver.ui.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.presenter.WelcomePresenter;
import com.byd.james.topspeedserver.presenter.contract.WelcomeContract;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.ImageLoader;
import com.byd.james.topspeedserver.utils.JumpUtils;
import com.byd.james.topspeedserver.utils.Pretreatment;
import com.byd.james.topspeedserver.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    @BindView(R.id.activity_welcome_iv)
    ImageView activityWelcomeIv;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        unbinder = ButterKnife.bind(this);
        mPresenter = new WelcomePresenter(this);
        setGuidePager();
    }

    private void setGuidePager() {
        //得到sharePreferences
        sharedPreferences=this.getSharedPreferences("check",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        isFirst=sharedPreferences.getBoolean("firstIn",true);

    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        //设置当前的Presenter
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        //展示错误信息
        if (error != null) {
            EventUtil.showToast(this, error);
        }
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<String> list) {
        //展示数据
        //获取随机的页数
        int pager= StringUtils.getRandomNum(0,list.size()-1);
        ImageLoader.loadImage(this,list.get(pager),activityWelcomeIv);
        //添加动画
        activityWelcomeIv.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
    }

    @Override
    public void jump2MainActivity() {
        if(!isFirst) {
            //跳到主界面
            JumpUtils.jumpToMainActivity(this, MainActivity.class);
            //添加跳转动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else{
            JumpUtils.jump(this,SplashActivity.class);
            finish();
            editor.putBoolean("firstIn",false);
        }
        editor.commit();
    }

}
