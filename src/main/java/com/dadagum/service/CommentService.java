package com.dadagum.service;

import com.dadagum.bean.Comment;
import com.dadagum.dto.CommentDto;

import java.util.List;

public interface CommentService {

    /**
     * 添加评论
     * @param comment 评论信息
     * @return
     */
    public List<String> makeComment(Comment comment);

    /**
     * 普通用户删除一个评论
     * @param comment_id
     * @param session_id
     * @return
     */
    public boolean deleteComment(int comment_id, int session_id);

    /**
     * 得到某一信息底下的评论
     * @param type_id
     * @param info_id
     * @return
     */
    public List<CommentDto> getSpecificComment(int type_id, int info_id);

}
