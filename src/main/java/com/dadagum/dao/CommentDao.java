package com.dadagum.dao;

import com.dadagum.bean.Comment;
import com.dadagum.dto.CommentDto;

import java.util.List;

public interface CommentDao {

    /**
     * 判断是否存在评论
     * @param comment_id 评论id
     * @return
     */
    public boolean hasComment(int comment_id);

    /**
     * 增加一个评论
     * @param comment 评论信息
     */
    public void addComment(Comment comment);

    /**
     * 删除一个评论
     * @param comment_id 评论id
     * @return
     */
    public boolean deleteComment(int comment_id);

    /**
     * 得到某一个信息底下的所有评论
     * @param type_id 类型id
     * @param info_id 信息id
     * @return
     */
    public List<CommentDto> getSpecificComment(int type_id, int info_id);

    /**
     * 返回一个评论的主人
     * @param comment_id 评论id
     * @return
     */
    public int getCommentSenderId(int comment_id);
}
