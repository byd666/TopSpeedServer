package com.byd.james.topspeedserver.presenter;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.byd.james.topspeedserver.model.bean.ImageBean;
import com.byd.james.topspeedserver.presenter.contract.SelectImageContract;
import com.byd.james.topspeedserver.utils.Pretreatment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by james on 2016/12/26.
 */

public class SelectImagePresenter implements SelectImageContract.Presenter{
    SelectImageContract.View mView;
    //进度条
    private ProgressDialog mDialog;
    private List<ImageBean> fList;
    private Context context;
    private HashSet<String> mDirPaths;
    private int totalCount=0;
    //存储文件夹中图片的数量
    private int mPicsSize;
    //图片数量最多的文件夹
    private File mImageDir;
    //存储图片的地址
    private List<String> addressList;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mDialog.dismiss();
        }
    };


    public SelectImagePresenter(ProgressDialog mDialog,Context context, @NonNull SelectImageContract.View view) {
        mView= Pretreatment.checkNotNull(view);
        mView.setPresenter(this);
        this.context=context;
        this.mDialog=mDialog;
        mDirPaths=new HashSet<>();
        loadData();
    }

    @Override
    public void loadData() {
        //从手机中取出图片，在通过view的接口传给View层
        mView.showContent(getAddress());
    }

    private List<ImageBean> getAddress() {
        fList=new ArrayList<>();
        //获取图片的地址..
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
           // Log.e("暂无外部存储", "getAddress: "+Environment.getExternalStorageState());
            return null;
        }
        //加载进度条
        mDialog=ProgressDialog.show(context,null,"加载中...");
        //开启子线程去遍历文件夹查找图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                //加载图片
                String firstImage=null;
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                //获取系统的ContentResolver外界的程序可
                //以通过ContentResolver接口访问ContentProvider提供的数据
                ContentResolver resolver=context.getContentResolver();

                //获取游标
                Cursor mCursor = resolver.query(uri, null
                        , MediaStore.Images.Media.MIME_TYPE+"=? or "
                        +MediaStore.Images.Media.MIME_TYPE+"=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                while(mCursor.moveToNext())
                {
                    String path=mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    if(firstImage==null)
                    {
                        firstImage=path;
                    }
                    File fileParent=new File(path).getParentFile();
                    if(fileParent==null)
                        continue;
                    String absolutePath = fileParent.getAbsolutePath();

                    ImageBean imageBean = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(absolutePath))
                    {
                        continue;
                    } else {
                        mDirPaths.add(absolutePath);
                        // 初始化imageFloder
                        imageBean = new ImageBean();
                        imageBean.setFileDir(absolutePath);
                        imageBean.setFirstImagePath(path);
                    }
                    //每个文件夹中图片的数量
                    int picSize = fileParent.list(new FilenameFilter()
                    {
                        @Override
                        public boolean accept(File dir, String filename)
                        {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    //图片总数
                    totalCount += picSize;
                    //记录每个文件夹图片的数量
                    imageBean.setCount(picSize);
                    fList.add(imageBean);

                    if(picSize>mPicsSize)
                    {
                        mPicsSize=picSize;
                        mImageDir=fileParent;
                    }

                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();
        return fList;
    }

    @Override
    public void attachView(Object view) {
        this.mView= (SelectImageContract.View) view;
    }

    @Override
    public void detachView() {
        this.mView=null;
    }
}
