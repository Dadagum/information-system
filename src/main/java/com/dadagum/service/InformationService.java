package com.dadagum.service;

import com.dadagum.api.ActivityInfoDto;
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
        // check if category exists
        if (categoryDao.getCountOfSpecificCategory(category) != 0){
            return informationDao.getAllCategoryInfo(category);
        }
        return null;
    }
}
