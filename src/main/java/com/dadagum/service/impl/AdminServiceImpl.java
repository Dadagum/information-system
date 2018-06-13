package com.dadagum.service.impl;

import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.AdminDao;
import com.dadagum.dao.CommentDao;
import com.dadagum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao dminDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private CommentDao commentDao;

    public boolean passActivity(int info_id){
        return dminDao.passActivity(info_id);
    }

    public boolean denyActivity(int type_id){
        return dminDao.denyActivity(type_id);
    }

    public boolean deleteActivity(int infoId){
        return activityDao.deleteActivity(infoId) ;
    }

    @Override
    public boolean deleteComment(int comment_id) {
        return commentDao.deleteComment(comment_id);
    }
}
