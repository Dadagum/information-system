package com.dadagum.service;

import com.dadagum.dto.UserDto;
import com.dadagum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * add a user
     * @param userDto
     */
    public void addUser(UserDto userDto){
        userDao.insertUser(userDto);
    }

    /**
     * check if username match password
     * @param userDto
     * @return true if match, false if not match
     */
    public boolean checkIfUsernameMatchPassword(UserDto userDto){
        return userDao.loginCheck(userDto) > 0;
    }

    public void update(UserDto userDto, int user_id){
        userDao.update(userDto, user_id);
    }

    public UserDto getPersonalInfo(int user_id){
        return userDao.getPersonalInfo(user_id);
    }
}
