package com.dadagum.service;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.InfoRequestDao;
import com.dadagum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean updateActivityInfo(ActivityInformation activityInformation){
        return activityDao.updateActivity(activityInformation) != 0;
    }

    public boolean deleteActivity(int infoId){
        return activityDao.deleteActivity(infoId) != 0;
    }

}
