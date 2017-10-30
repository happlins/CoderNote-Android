package com.example.codernote.bean

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Jaylin
 */
public class CreateNoteResponse extends BaseResponse {

    @JSONField(name = "NoteId")
    private String noteId;

    public CreateNoteResponse(int code, String msg) {
        super(code, msg);
    }

    public CreateNoteResponse(int code, String message, String noteId) {
        super(code, message);
        this.noteId = noteId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
