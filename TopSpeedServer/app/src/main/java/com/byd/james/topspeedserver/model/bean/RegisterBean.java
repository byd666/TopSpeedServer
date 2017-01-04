package com.byd.james.topspeedserver.model.bean;

/**
 * Created by james on 2016/12/29.
 */

public class RegisterBean {
    //注册接口的实体类
    private String phone;
    private String password;
    private String nick_name;
    private String vCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
