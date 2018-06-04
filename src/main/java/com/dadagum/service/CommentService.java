package com.dadagum.service;

import com.dadagum.bean.Comment;
import com.dadagum.dao.CategoryDao;
import com.dadagum.dao.CommentDao;
import com.dadagum.dao.InformationDao;
import com.dadagum.dto.CommentDto;
import com.dadagum.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private CategoryDao categoryDao;

    public List<String> makeComment(Comment comment){
        List<String> result = DateValidator.validateComment(comment);
        if (result != null) return result;
        // check if specific information exists and category exists;
        if(categoryDao.hasCategory(comment.getType_id()) && informationDao.hasInfo(comment.getInfo_id(), comment.getType_id())){
            // try to know if it is a comment or reply
            if (comment.getParent_id() == 0) { // just a comment
                commentDao.addComment(comment);
                return null;
            }
            // reply
            // check if parent comment exists;
            if (commentDao.hasComment(comment.getParent_id())) {
                commentDao.addComment(comment);
                return null;
            }
            // invalid reply
        }
        result = new ArrayList<>();
        result.add("要评论的信息不存在，评论失败！");
        return result;
    }

    /**
     * delete comment method for normal users
     * @param comment_id
     * @param session_id
     * @return
     */
    public boolean deleteComment(int comment_id, int session_id){
        return comment_id == session_id && commentDao.deleteComment(comment_id);
    }

    /**
     * delete comment method for admin
     * @param comment_id
     * @return
     */
    public boolean deleteComment(int comment_id){
        return commentDao.deleteComment(comment_id);
    }

    public List<CommentDto> getSpecificComment(int type_id, int info_id){
        return commentDao.getSpecificComment(type_id, info_id);
    }

}
