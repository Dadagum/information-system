package com.dadagum.service;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import com.dadagum.dao.UserDao;
import com.dadagum.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * add a user
     * @param user
     */
    public List<String> addUser(User user, String r_password){
        List<String> msg = DateValidator.validateAllRegister(user, r_password);
        if (msg == null || !userDao.hasUser(user)){
            userDao.insertUser(user);
            return null;
        }
        // default a normal user so there is no need to insert priority table
        return msg;
    }

    /**
     * login check
     * @param user
     * @return user_id if the user exists
     */
    public int loginCheck(User user){
        return userDao.isPwdMatchName(user) ? userDao.getUserId(user) : 0;
    }

    public boolean update(User user){
        return userDao.update(user) == 1;
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

}
