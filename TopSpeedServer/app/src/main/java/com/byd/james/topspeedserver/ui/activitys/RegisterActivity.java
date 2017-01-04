package com.byd.james.topspeedserver.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.model.bean.Vcode;
import com.byd.james.topspeedserver.presenter.RegisterPresenter;
import com.byd.james.topspeedserver.presenter.contract.RegisterContract;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.Pretreatment;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View{
    RegisterPresenter prester;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.record_second)
    TextView recordSecond;
    @BindView(R.id.register_registe)
    TextView registerRegiste;
    @BindView(R.id.register_phone)
    EditText etPhone;

    private String mPhone;
    private int mSecond=60;
    private Timer timer;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            recordSecond.setText(msg.arg1+"秒");
            startTimer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
        mPresenter=new RegisterPresenter(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //点击返回退出当前activity
            finish();
            }
        });
    }

    private void initView() {
        timer=new Timer(true);
    }

    @OnClick({R.id.record_second, R.id.register_registe})
    public void onClick(View view) {
        String trim = etPhone.getText().toString().trim();
        if(mSecond<60 && mSecond>0 && trim==null && !isMobileNum(trim)){
            Toast.makeText(this, "手机号码不合法，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }else {
            mPhone=trim;
            switch (view.getId()) {
                case R.id.record_second:
                    //记录秒数，点击发送，向后台提供手机号，开始倒计时，
                    //改变textView的显示内容，到0时停止
                    recordSecond.setText(mSecond + "秒");
                    startTimer();
                    mPresenter.onRefresh();
                    break;
                case R.id.register_registe:
                    //点击注册，向后台提供手机号，密码，昵称，验证码
                    //并返回登陆页面

                    break;
            }
        }
    }
    public static boolean isMobileNum(String telNum){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }
    private void startTimer() {
        //计时的方法
        if(timer==null)
        {
            timer=new Timer();
        }
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                mSecond--;
                Message message=new Message();
                message.arg1=mSecond;
                handler.sendMessage(message);//发送消息
            }
        };
        //1秒执行一次
        timer.schedule(timerTask,1000);
        if(mSecond==0)
        {
            timer.cancel();
            timer=null;
            recordSecond.setText("发送");
            mSecond=60;
        }
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public Context getContext() {
        return this;
    }
    //获取电话号码的方法
    @Override
    public String getPhone() {
        return mPhone;
    }
    //展示数据的方法,获得message ok
    @Override
    public void showContent(Vcode message) {
        String s = message.getvCode();
        if(s!=null && s.equals("ok"))
        {
            Toast.makeText(this, "验证码发送成功！", Toast.LENGTH_SHORT).show();
            Log.e("AAAA","发送成功");
        }
    }
    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter= (RegisterPresenter) Pretreatment.checkNotNull(presenter);
    }
    @Override
    public void showError(String error) {
        EventUtil.showToast(this,error);
    }
}
