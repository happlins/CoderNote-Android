package com.example.codernote.bean;

/**
 * 登录请求响应Model
 * @author Jaylin
 */
public class LoginResponseModel extends BaseResponse {

    private String token;

    public LoginResponseModel() {}

    public LoginResponseModel(int code, String message) {
        super(code, message);
    }

    public LoginResponseModel(int code, String message, String token) {
        super(code, message);
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
