package com.dadagum.service.impl;

import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.AdminDao;
import com.dadagum.dao.CommentDao;
import com.dadagum.dao.InformationDao;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<ActivityInfoDto> getAllActivityInfo() {
        return activityDao.getAllInfoList();
    }

    public boolean passActivity(int info_id){
        return adminDao.passActivity(info_id);
    }

    public boolean denyActivity(int type_id){
        return adminDao.denyActivity(type_id);
    }

    @Transactional
    public boolean deleteActivity(int infoId){
        informationDao.deleteRequest(2, infoId);
        return activityDao.deleteActivity(infoId) ;
    }

    @Override
    public boolean deleteComment(int comment_id) {
        return commentDao.deleteComment(comment_id);
    }
}
