package com.dadagum.service.impl;

import com.dadagum.bean.User;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dao.UserDao;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import com.dadagum.service.UserService;
import com.dadagum.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityDao activityDao;

    public List<String> addUser(User user, String r_password){
        List<String> msg = DateValidator.validateAllRegister(user, r_password); // TODO
        if (msg == null && !userDao.hasUser(user)){
            userDao.addUser(user);
            return null;
        }
        // TODO
        msg = new ArrayList<>();
        msg.add("error");
        return msg;
    }

    public int loginCheck(User user){
        return userDao.isPwdMatchName(user) ? userDao.getUserIdByName(user.getUsername()) : 0;
    }

    public boolean update(User user){
        return userDao.update(user);
    }

    public UserDto getPersonalInfo(int user_id){
        return userDao.getPersonalInfo(user_id);
    }

    public List<ActivityInfoDto> getFavorList(int user_id){
        return userDao.getFavorList(user_id);
    }

    public String getUserPriority(int user_id){
        if (userDao.isNormalUser(user_id)) return null;
        return userDao.getPriority(user_id);
    }

    public User getUser(String username){
        return userDao.getUserByName(username);
    }

    public boolean addFavorActivity(int user_id, int info_id){
        // 检查活动是否存在
        if (activityDao.hasActivivy(info_id)){
            userDao.addFavor(user_id, info_id);
            return true;
        }
        return false;
    }
}
