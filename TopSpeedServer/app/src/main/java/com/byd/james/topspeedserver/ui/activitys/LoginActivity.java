package com.byd.james.topspeedserver.ui.activitys;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.utils.JumpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    TextInputLayout username;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.remember_password)
    CheckBox rememberPassword;
    @BindView(R.id.remember_password_tv)
    TextView rememberPasswordTv;
    @BindView(R.id.autologin)
    CheckBox autologin;
    @BindView(R.id.login_autologin_tv)
    TextView loginAutologinTv;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_forget_password)
    TextView loginForgetPassword;
    @BindView(R.id.login_thirdParty)
    TextView loginThirdParty;
    @BindView(R.id.login_qq)
    TextView loginQq;
    @BindView(R.id.login_weixin)
    TextView loginWeixin;
    @BindView(R.id.login_weibo)
    TextView loginWeibo;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);

    }
    @OnClick(R.id.login_register)
    public void loginClick(View view) {
        switch (view.getId())
        {
            case R.id.login_register:
                JumpUtils.jump(this,RegisterActivity.class);
                break;
        }
    }
}
