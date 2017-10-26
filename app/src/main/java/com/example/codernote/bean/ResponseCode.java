package com.example.codernote.bean;
/**
 * 响应代码
 *
 * @author Jaylin
 */
public enum ResponseCode {
    UNKNOWN("未知错误"),
    SUCCESS_REGISTER("注册成功!"),
    SUCCESS_LOGIN("登录成功！"),
    ERROR_USER_INEXISTENT("用户不存在！"),
    ERROR_USERNAME_EXIST("用户名已存在！"),
    ERROR_EMPTY_USERNAME("用户名不可为空！"),
    ERROR_EMPTY_PASSWORD("密码不可为空！"),
    ERROR_TOOLONG_USERNAME("用户名不得超过32个字符！"),
    ERROR_INVALID_PASSWORD("密码错误！"),
    ERROR_INVALID_TOKEN("无效的Token！"),

    SUCCESS_SUBMIT_NOTE("笔记添加成功！"),
    ERROR_SUBMIT_NOTE("笔记添加失败！"),

    ERROR_INVALID_NOTE("无效的笔记！"),

    SUCCESS_DELETE("删除成功！")
    ;

    /**
     * 代码
     */
    private int code;

    /**
     * 响应内容
     */
    private String msg;

    static class Counter {
        static int id = 10000;
    }

    ResponseCode(String msg) {
        this.code = Counter.id++;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(ResponseCode.ERROR_USERNAME_EXIST.getCode());
        System.out.println(ResponseCode.UNKNOWN.getCode());
    }
}
