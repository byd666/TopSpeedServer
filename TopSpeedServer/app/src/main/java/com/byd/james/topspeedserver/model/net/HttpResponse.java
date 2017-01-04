package com.byd.james.topspeedserver.model.net;

/**
 * Created by james on 2016/12/29.
 */

public class HttpResponse<T> {
    private int code;
    private String message;
    T ret;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}
