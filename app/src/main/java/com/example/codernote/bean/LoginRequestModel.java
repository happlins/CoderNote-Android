package edu.cap.jaylin.codernote.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 登录请求Model
 *
 * @author Jaylin
 */
public class LoginRequestModel {

    /**
     * 用户名
     */
    @JSONField(name = "username")
    private String userName;

    /**
     * 密码
     */
    @JSONField(name = "password")
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
