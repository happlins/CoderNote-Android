package edu.cap.jaylin.codernote.model;

import java.util.Date;

/**
 * 用户Model
 *
 * @author Jaylin
 */
public class User {
    /**
     * 用户编号
     */
    private Object userId;

    /**
     * 加入的群组
     */
    private Object team;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 是否有效（删除）
     */
    private Boolean userValid;

    public User() { }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    private User(Builder builder) {
        setUserId(builder.userId);
        setTeam(builder.team);
        setUserName(builder.userName);
        setPassWord(builder.passWord);
        setGmtCreate(builder.gmtCreate);
        setGmtModified(builder.gmtModified);
        setUserValid(builder.userValid);
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getTeam() {
        return team;
    }

    public void setTeam(Object team) {
        this.team = team;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getUserValid() {
        return userValid;
    }

    public void setUserValid(Boolean userValid) {
        this.userValid = userValid;
    }


    public static final class Builder {
        private Object userId;
        private Object team;
        private String userName;
        private String passWord;
        private Date gmtCreate;
        private Date gmtModified;
        private Boolean userValid;

        public Builder() {}

        public Builder withUserId(Object val) {
            userId = val;
            return this;
        }

        public Builder withTeam(Object val) {
            team = val;
            return this;
        }

        public Builder withUserName(String val) {
            userName = val;
            return this;
        }

        public Builder withPassWord(String val) {
            passWord = val;
            return this;
        }

        public Builder withGmtCreate(Date val) {
            gmtCreate = val;
            return this;
        }

        public Builder withGmtModified(Date val) {
            gmtModified = val;
            return this;
        }

        public Builder withUserValid(Boolean val) {
            userValid = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}