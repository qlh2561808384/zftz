package com.datanew.dto;

/**
 * Created by ghost.he
 */
//单点登录返回数据
public class SSOLoginDTO {

    //功能点url
    private String redirect_to;

    //信息
    private String msg;

    public String getRedirect_to() {
        return redirect_to;
    }

    public void setRedirect_to(String redirect_to) {
        this.redirect_to = redirect_to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
