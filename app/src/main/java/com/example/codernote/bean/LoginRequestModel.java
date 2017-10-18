package com.example.codernote.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 登录请求Model
 *
 * @author Jaylin
 */
public class LoginRequestModel {

    /**
     * 用户名
     */
    @SerializedName("username")
    private String userName;

    /**
     * 密码
     */
    @SerializedName("password")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
