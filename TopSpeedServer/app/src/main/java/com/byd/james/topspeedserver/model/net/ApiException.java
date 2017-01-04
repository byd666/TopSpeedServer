package com.byd.james.topspeedserver.model.net;

/**
 * Created by james on 2016/12/29.
 */
//错误的类,返回错误信息和错误码，
public class ApiException extends Exception{
    private int code;
    private String errorMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String s) {
        super(s);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
