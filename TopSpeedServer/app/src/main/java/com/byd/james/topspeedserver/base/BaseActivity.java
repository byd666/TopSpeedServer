package com.byd.james.topspeedserver.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.app.App;
import com.byd.james.topspeedserver.utils.PreUtils;
import com.byd.james.topspeedserver.utils.ScreenUtil;
import com.byd.james.topspeedserver.widget.theme.ColorRelativeLayout;
import com.byd.james.topspeedserver.widget.theme.Theme;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity  {
    protected T mPresenter;
    protected Unbinder unbinder;
    protected boolean mActive;//是否被销毁
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //初始化
        init();
    }

    protected void init() {
        //设置沉浸式模式：去除状态栏
        setTranslucentStatus(true);
        //选中主题后变色
        onPreCreate();
        //记录你所创建的activity
        App.getInstance().registerActivity(this);
        mActive=true;//不销毁
        if(mPresenter!=null)
        {
            mPresenter.attachView(this);
        }
    }


    private void setTranslucentStatus(boolean b) {
        //倘若你的工具版本大于19则执行下面操作
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            //通过window管理器获得布局填充器
            WindowManager.LayoutParams winParams = win.getAttributes();
            //获取主题标签，返回值为十六进制的数
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (b) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private void onPreCreate() {
        //得到的保存的主题颜色
        Theme theme = PreUtils.getCurrentTheme(this);
        switch (theme) {
            case Blue:
               // setTheme(R.style.BlueTheme);
                break;
            case Red:
                //setTheme(R.style.RedTheme);
                break;
            case Brown:
                //setTheme(R.style.BrownTheme);
                break;
            case Green:
                //setTheme(R.style.GreenTheme);
                break;
            case Purple:
                //setTheme(R.style.PurpleTheme);
                break;
            case Teal:
                //setTheme(R.style.TealTheme);
                break;
            case Pink:
                //setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
               // setTheme(R.style.DeepPurpleTheme);
                break;
            case Orange:
                //setTheme(R.style.OrangeTheme);
                break;
            case Indigo:
               // setTheme(R.style.IndigoTheme);
                break;
            case LightGreen:
                //setTheme(R.style.LightGreenTheme);
                break;
            case Lime:
               // setTheme(R.style.LimeTheme);
                break;
            case DeepOrange:
               // setTheme(R.style.DeepOrangeTheme);
                break;
            case Cyan:
               // setTheme(R.style.CyanTheme);
                break;
            case BlueGrey:
                //setTheme(R.style.BlueGreyTheme);
                break;
            case Black:
                //setTheme(R.style.BlackTheme);
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onRestart();
        setTitleHeight(getRootView(this));
    }

    private void setTitleHeight(View rootView) {
        //设置标题栏的高度
        if(rootView!=null)
        {
            ColorRelativeLayout title=(ColorRelativeLayout)rootView.findViewById(R.id.title);
            if(Build.VERSION.SDK_INT <Build.VERSION_CODES.KITKAT)
            {
                //sdk版本小于19时重新设置标题栏的高度
                if (title != null) {
                    ViewGroup.LayoutParams lp = title.getLayoutParams();
                    lp.height = ScreenUtil.dip2px(this, 48);
                    title.setLayoutParams(lp);
                    title.setPadding(0, 0, 0, 0);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().unregisterActivity(this);
        if(mPresenter!=null)
        {
            mPresenter.detachView();
        }
        if(unbinder!=null)
        {
            unbinder.unbind();
        }
        mActive=false;
    }

    //获得一个容器,是一个垂直的LinearLayout
    protected View getRootView(Activity context) {
        return ((ViewGroup)context.findViewById(android.R.id.content)).getChildAt(0);
    }

}
