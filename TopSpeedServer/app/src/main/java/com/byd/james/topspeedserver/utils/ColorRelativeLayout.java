package com.byd.james.topspeedserver.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.byd.james.topspeedserver.widget.theme.ColorUiInterface;
import com.byd.james.topspeedserver.widget.theme.ViewAttributeUtil;

/**
 * Created by james on 2016/12/22.
 */

public class ColorRelativeLayout extends RelativeLayout implements ColorUiInterface{
    private int attrs_background=-1;
    public ColorRelativeLayout(Context context) {
        super(context);
    }

    public ColorRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs_background= ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int attrs_background) {
        super(context, attrs, defStyleAttr);
        this.attrs_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override//获得布局中的控件
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        //设置主题
        if(attrs_background!=-1)
        {
            //当attrs_background不是-1时，说明有别的类使用此控件
            ViewAttributeUtil.applyBackgroundDrawable(this,themeId,attrs_background);
        }
    }
}
