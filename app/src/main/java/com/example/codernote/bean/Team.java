package com.example.codernote.bean;

import java.util.Date;

/**
 * 群组Model
 *
 * @author Jaylin
 */
public class Team {
    /**
     * 群组编号
     */
    private Object teamId;

    /**
     * 群组名称
     */
    private String teamName;

    /**
     * 创建人
     */
    private Object originator;

    /**
     * 标签
     */
    private Integer tag;

    /**
     * 成员
     */
    private Object member;

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
    private Boolean teamValid;

    public Object getTeamId() {
        return teamId;
    }

    public void setTeamId(Object teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public Object getOriginator() {
        return originator;
    }

    public void setOriginator(Object originator) {
        this.originator = originator;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Object getMember() {
        return member;
    }

    public void setMember(Object member) {
        this.member = member;
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

    public Boolean getTeamValid() {
        return teamValid;
    }

    public void setTeamValid(Boolean teamValid) {
        this.teamValid = teamValid;
    }
}