package com.dadagum.service;

import com.dadagum.bean.Category;
import com.dadagum.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * add a new info category.
     * @param category
     * @return
     */
    public boolean addType(Category category){
        categoryDao.addType(category);
        return true;
    }

}
