package com.dadagum.dto;

import com.dadagum.bean.Comment;
import com.dadagum.util.ConvertUtil;

public class CommentDto {
    private String username;
    private int type_id;
    private int info_id;
    private int parent_id;
    private String comment;
    private String create_time;

    public CommentDto(Comment comment){
        type_id = comment.getType_id();
        info_id = comment.getInfo_id();
        parent_id = comment.getParent_id();
        this.comment = comment.getComment();
        create_time = comment.getCreate_time();
    }

    public CommentDto(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
