package com.dadagum.service.impl;

import com.dadagum.bean.Comment;
import com.dadagum.dao.CategoryDao;
import com.dadagum.dao.CommentDao;
import com.dadagum.dao.InformationDao;
import com.dadagum.dto.CommentDto;
import com.dadagum.service.CommentService;
import com.dadagum.util.ConvertUtil;
import com.dadagum.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private CategoryDao categoryDao;

    public List<String> makeComment(Comment comment){
        List<String> result = DateValidator.validateComment(comment);
        if (result != null) return result;
        // 检查对应信息是否存在
        if(categoryDao.hasCategory(comment.getType_id()) && informationDao.hasInfo(comment.getInfo_id(), comment.getType_id())){
            Date now = new Date();
            comment.setCreate_time(ConvertUtil.DateToString(now));
            // 检查是回复还是评论
            if (comment.getParent_id() == 0) {
                // 只是一个评论
                commentDao.addComment(comment);
                return null;
            }
            // 是一个回复
            // 检查被回复的评论是否存在
            if (commentDao.hasComment(comment.getParent_id())) {
                commentDao.addComment(comment);
                return null;
            }
        }
        // 被回复的评论不存在
        result = new ArrayList<>();
        result.add("要评论的信息不存在，评论失败！");
        return result;
    }

    public boolean deleteComment(int comment_id, int session_id){
        try {
            return commentDao.getCommentSenderId(comment_id) == session_id && commentDao.deleteComment(comment_id);
        } catch (RuntimeException e){
            return false;
        }
    }


    public List<CommentDto> getSpecificComment(int type_id, int info_id){
        return commentDao.getSpecificComment(type_id, info_id);
    }

}
