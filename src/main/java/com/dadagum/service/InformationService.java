package com.dadagum.service;

import com.dadagum.api.ActivityInfoDto;
import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.TeamRequest;
import com.dadagum.dao.CategoryDao;
import com.dadagum.dao.InformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * add a activity
     * @param activityInfoDto
     */
    public void addActivity(ActivityInfoDto activityInfoDto){
        informationDao.addActivity(activityInfoDto);
    }

    /**
     * view specific category info
     * @param category
     * @return
     */
    public List<?> getSpecificCategoryInfo(String category){
        return categoryDao.getCountOfSpecificCategory(category) == 0 ? null : informationDao.getAllCategoryInfo(category);
    }

    public boolean updateActivityInfo(ActivityInformation activityInformation){
        return informationDao.updateActivity(activityInformation) != 0;
    }

    public boolean deleteActivity(int infoId){
        return informationDao.deleteActivity(infoId) != 0;
    }

    public void postTeamRequest(TeamRequest request){
        informationDao.addTeamRequest(request);
    }
}
