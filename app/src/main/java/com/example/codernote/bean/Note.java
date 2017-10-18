package edu.cap.jaylin.codernote.model;

import java.util.Date;

/**
 * 笔记Model
 *
 * @author Jaylin
 */
public class Note {
    /**
     * 笔记编号
     */
    private Object noteId;

    /**
     * 名称
     */
    private String title;

    /**
     * 作者
     */
    private Object author;

    /**
     * 内容
     */
    private String content;

    /**
     * 目录
     */
    private Object dictionary;

    /**
     * 标签
     */
    private Object tag;

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
    private Boolean noteValid;

    public Note(String noteId) {
        this.noteId = noteId;
    }

    public Object getNoteId() {
        return noteId;
    }

    public void setNoteId(Object noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Object getDictionary() {
        return dictionary;
    }

    public void setDictionary(Object dictionary) {
        this.dictionary = dictionary;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
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

    public Boolean getNoteValid() {
        return noteValid;
    }

    public void setNoteValid(Boolean noteValid) {
        this.noteValid = noteValid;
    }
}