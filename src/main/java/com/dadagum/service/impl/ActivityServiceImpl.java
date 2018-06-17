package com.dadagum.service.impl;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.InformationDao;
import com.dadagum.dao.UserDao;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InformationDao informationDao;

    @Transactional
    public void addActivity(ActivityInformation activity){
        int info_id = activityDao.addActivity(activity);
        System.out.println("成功插入, info_id" + info_id);
        informationDao.addRequest(2, info_id);
    }

    public boolean updateActivity(ActivityInformation activity, int user_id){
        activity.setUser_id(user_id);
        return activityDao.updateActivity(activity);
    }

    public List<ActivityInfoDto> getPassInfoList(){
        //return activityDao.getPassInfoList();
        return activityDao.getAllInfoList();
    }

    public ActivityInfoDto getSpecificInfo(int info_id){
        return activityDao.getSpecificInfo(info_id);
    }

}
