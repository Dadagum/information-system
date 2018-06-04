package com.dadagum.service;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.InfoRequestDao;
import com.dadagum.dao.UserDao;
import com.dadagum.dto.ActivityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserDao userDao;

    private InfoRequestDao requestDao;

    /**
     * add a activity
     * @param activity
     */
    @Transactional
    public boolean addActivity(ActivityInformation activity, int user_id){
        // to check if the user is org
        if (userDao.getPriority(user_id).equals("org")){
            requestDao.addRequest(2, activity.getInfo_id());
            activityDao.addActivity(activity);
            return true;
        }
        return false;
    }

    public boolean updateActivity(ActivityInformation activity, int user_id){
        activity.setUser_id(user_id);
        return userDao.getPriority(user_id).equals("org") && activityDao.updateActivity(activity) == 1;
    }

    public List<ActivityInfoDto> getPassInfoList(){
        return activityDao.getPassInfoList();
    }

    public ActivityInfoDto getSpecificInfo(int info_id){
        return activityDao.getSpecificInfo(info_id);
    }

    public boolean deleteActivity(int infoId){
        return activityDao.deleteActivity(infoId) != 0;
    }

    public boolean addFavorActivity(int user_id, int info_id){
        // check if activity exists
        if (activityDao.getCountByInfoId(info_id) != 0){
            activityDao.addFavor(user_id, info_id);
            return true;
        }
        return false;
    }
}
