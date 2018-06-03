package com.dadagum.service;

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
     * view specific category info
     * @param category
     * @return
     */
    public List<?> getSpecificCategoryInfo(String category){
        return categoryDao.hasCategory(category) ? informationDao.getAllCategoryInfo(category) : null;
    }


}
