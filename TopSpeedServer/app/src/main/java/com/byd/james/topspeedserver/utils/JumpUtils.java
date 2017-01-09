package com.byd.james.topspeedserver.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.byd.james.topspeedserver.ui.activitys.SelectImageActivity;
import com.byd.james.topspeedserver.ui.activitys.ShareMoodActivity;
import com.byd.james.topspeedserver.ui.activitys.VideoListActivity;
import com.byd.james.topspeedserver.ui.activitys.WelcomeActivity;

import java.util.ArrayList;

/**
 * Created by james on 2016/12/21.
 */

public class JumpUtils {


    public static void jumpToMainActivity(Context context, Class<?> clazz) {
        jump(context, clazz);
        //跳转后关闭welcome页面
        ((WelcomeActivity) context).finish();
    }

    //基本的跳转方法
    public static void jump(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void jumpToGaoDeActivity(ShareMoodActivity context, Class<?> clazz, int requestCode) {
        //跳转到高德地图，并返回定位信息
        Intent intent = new Intent(context, clazz);
        context.startActivityForResult(intent,requestCode);
    }
    public static void jumpToSelectImageActivity(ShareMoodActivity context, Class<?> clazz, int requestCode) {
        Intent intent = new Intent(context, clazz);
        context.startActivityForResult(intent,requestCode);
    }
    public static void jumpToSelectImage2Activity(SelectImageActivity context, Class<?> clazz, ArrayList<String> list, String fileName,int RequestCode) {

        Intent intent = new Intent(context, clazz);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("list", list);
        bundle.putString("filePath",fileName);
        intent.putExtras(bundle);
        context.startActivityForResult(intent,RequestCode);
    }
    public static void jump2ShareMoodActivity(Context context, Class<?> clazz,ArrayList<String> list) {

        Intent intent = new Intent(context, clazz);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("imageShow", list);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    //跳转到更多的activity
    public static void jump2VideoListActivity(Context context, String catalogId, String title) {
        Intent intent = new Intent(context, VideoListActivity.class);
        intent.putExtra("catalogId", catalogId);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }
}
