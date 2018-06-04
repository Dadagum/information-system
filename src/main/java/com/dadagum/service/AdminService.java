package com.dadagum.service;

import com.dadagum.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public boolean passActivity(int type_id, int info_id){
        return adminDao.passActivity(type_id, info_id) == 1;
    }

    public boolean denyActivity(int type_id, int info_id){
        return adminDao.denyActivity(type_id, info_id) == 1;
    }
}
