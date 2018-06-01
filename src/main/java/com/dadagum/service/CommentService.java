package com.dadagum.service;

import com.dadagum.bean.Comment;
import com.dadagum.dao.CategoryDao;
import com.dadagum.dao.CommentDao;
import com.dadagum.dao.InformationDao;
import com.dadagum.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private CategoryDao categoryDao;

    public boolean makeComment(Comment comment, int user_id){
        // check if specific information exists and category exists;
        if(categoryDao.hasCategory(comment.getType_id()) && informationDao.hasInfo(comment.getInfo_id(), comment.getType_id())){
            // try to know if it is a comment or reply
            if (comment.getParent_id() == 0) { // just a comment
                commentDao.addComment(comment, user_id);
                return true;
            }
            // reply
            // check if parent comment exists;
            if (commentDao.hasComment(comment.getParent_id())) {
                commentDao.addComment(comment, user_id);
                return true;
            }
            // invalid reply
        }
        return false;
    }

    public boolean deleteComment(int comment_id, int session_id){
        return comment_id == session_id && commentDao.deleteComment(comment_id);
    }

    public List<CommentDto> getSpecificComment(String category, int info_id){
        // check if the params exist
        if (categoryDao.hasCategory(category)){
            // get type_id
            int type_id = categoryDao.getIdByCategory(category);
            return commentDao.getSpecificComment(type_id, info_id);
        }
        return null;
    }
}
