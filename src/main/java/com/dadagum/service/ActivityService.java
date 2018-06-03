package com.dadagum.service;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dto.ActivityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    /**
     * add a activity
     * @param activityInfoDto
     */
    public void addActivity(ActivityInfoDto activityInfoDto){
        activityDao.addActivity(activityInfoDto);
    }

    public boolean updateActivityInfo(ActivityInformation activityInformation){
        return activityDao.updateActivity(activityInformation) != 0;
    }

    public boolean deleteActivity(int infoId){
        return activityDao.deleteActivity(infoId) != 0;
    }

}
