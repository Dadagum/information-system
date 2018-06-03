package com.dadagum.service;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import com.dadagum.dao.UserDao;
import com.dadagum.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<String> msg = UserValidator.validateAllRegister(user, r_password);
        if (msg == null){
            userDao.insertUser(user);
            return null;
        }
        return msg;
    }

    /**
     * check if username match password
     * @param user
     * @return true if match, false if not match
     */
    public boolean checkIfUsernameMatchPassword(User user){
        return userDao.loginCheck(user) > 0;
    }

    public boolean update(User user, int user_id){
        if (user.getUser_id() != user_id) return false;
        userDao.update(user);
        return true;
    }

    public UserDto getPersonalInfo(int user_id){
        return userDao.getPersonalInfo(user_id);
    }

    public List<ActivityInfoDto> getFavorList(int user_id){
        return userDao.getFavorList(user_id);
    }
}
