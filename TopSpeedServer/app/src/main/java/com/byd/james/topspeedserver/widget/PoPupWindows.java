package com.byd.james.topspeedserver.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;

/**
 * Created by james on 2016/12/23.
 */

public class PoPupWindows extends PopupWindow {
    private View mView;
    private TextView fromCamera,fromPhone,fromNet,cancle;
    public PoPupWindows(Context context, View.OnClickListener itemClickEvent ) {
        super(context);
        //获取布局管理器
        LayoutInflater layout= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView=layout.inflate(R.layout.popup_layout,null);
        initView(mView);
        //
        fromCamera.setOnClickListener(itemClickEvent);
        fromPhone.setOnClickListener(itemClickEvent);
        fromNet.setOnClickListener(itemClickEvent);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dismiss();
            }
        });
        //设置弹出体的属性
        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置可点击
        this.setFocusable(true);
        //设置淡出和弹出的动画
        this.setAnimationStyle(R.style.animationPopupWindow);
        ColorDrawable drawable=new ColorDrawable(Color.WHITE);
        this.setBackgroundDrawable(drawable);

    }

    private void initView(View mView) {
        fromCamera= (TextView) mView.findViewById(R.id.popup_window_camera);
        fromPhone= (TextView) mView.findViewById(R.id.popup_window_from_phone);
        fromNet= (TextView) mView.findViewById(R.id.popup_window_from_net);
        cancle= (TextView) mView.findViewById(R.id.popup_window_cancle);
    }


}
