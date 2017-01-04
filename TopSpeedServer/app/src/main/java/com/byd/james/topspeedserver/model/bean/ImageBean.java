package com.byd.james.topspeedserver.model.bean;

import java.io.Serializable;

/**
 * Created by james on 2016/12/26.
 */

public class ImageBean implements Serializable{
    //图片文件夹的路径
    private String fileDir;
    //文件夹的名称
    private String fileName;
    //第一张图片的地址
    private String firstImagePath;
    //图片的数量
    private int count;

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
        int lastImage=this.fileDir.lastIndexOf("/");
        this.fileName=this.fileDir.substring(lastImage);
    }

    public String getFileName() {
        return fileName;
    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
