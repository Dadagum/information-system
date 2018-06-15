package com.dadagum.dao.impl;

import com.dadagum.bean.User;
import com.dadagum.dao.UserDao;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    static final String USER_TABLE = "user";

    static final String FAVOR_TABLE = "favor";

    static final String PRIORITY_TABLE = "priority";

    private static final String INSERT_USER = "INSERT INTO " + USER_TABLE + " VALUES(null,?,?,?)";

    private static final String GET_ID_BY_NAME_AND_PWD = "SELECT user_id FROM " + USER_TABLE + " WHERE username=?";

    private static final String UPDATE_PERSONAL_INFO = "UPDATE " + USER_TABLE + " SET password = ? WHERE user_id = ?";

    private static final String GET_PERSONAL_INFO = "SELECT username, email FROM " + USER_TABLE + " WHERE user_id = ?";

    private static final String GET_USER_PRIORITY = "SELECT identity from " + PRIORITY_TABLE + " WHERE user_id = ?";

    private static final String GET_FAVOR_LIST = "SELECT org_name, introduction, name, start_time, end_time FROM " + FAVOR_TABLE + " NATURAL JOIN " + ActivityDaoImpl.ACTIVITY_TABLE + " WHERE user_id = ?";

    private static final String HAS_USER_REGISTER = "SELECT count(*) FROM " + USER_TABLE + " WHERE username = ? OR email = ?";

    private static final String IS_NORMAL_USER = "SELECT count(*) FROM " + PRIORITY_TABLE + " WHERE user_id = ?";

    private static final String IS_MATCH_PWD_NAMD = "SELECT count(*) FROM " + USER_TABLE + " WHERE username = ? AND password=?";

    private static final String GET_USER_BY_NAME = "SELECT * FROM " + USER_TABLE + " WHERE username = ?";

    private static final String ADD_FAVOR = "INSERT INTO " + UserDaoImpl.FAVOR_TABLE + " VALUES(?, 2, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user){
        jdbcTemplate.update(INSERT_USER, user.getUsername(), user.getEmail(), user.getPassword());
    }

    public boolean update(User user){
        return jdbcTemplate.update(UPDATE_PERSONAL_INFO, user.getPassword(), user.getUser_id()) == 1;
    }

    public UserDto getPersonalInfo(int user_id){
        final UserDto userDto = new UserDto();
        jdbcTemplate.query(GET_PERSONAL_INFO, new Object[]{user_id}, resultSet -> {
            userDto.setEmail(resultSet.getString("email"));
            userDto.setUsername(resultSet.getString("username"));
        });
        return userDto;
    }

    public String getPriority(int user_id){
        return jdbcTemplate.queryForObject(GET_USER_PRIORITY, new Object[]{user_id}, String.class);
    }

    public List<ActivityInfoDto> getFavorList(int user_id){
        List<ActivityInfoDto> result = new ArrayList<>();
        jdbcTemplate.query(GET_FAVOR_LIST, new Object[]{user_id}, resultSet -> {
            ActivityInfoDto tmp = new ActivityInfoDto();
            tmp.setOrg_name(resultSet.getString("org_name"));
            tmp.setIntroduction(resultSet.getString("introduction"));
            tmp.setName(resultSet.getString("name"));
            tmp.setStart_time(resultSet.getString("start_time"));
            tmp.setEnd_time(resultSet.getString("end_time"));
            tmp.setInfo_id(resultSet.getInt("info_id"));
            tmp.setType_id(resultSet.getInt("type_id"));
            result.add(tmp);
        });
        return result;
    }

    public boolean hasUser(User user){
        return jdbcTemplate.queryForObject(HAS_USER_REGISTER, new Object[]{user.getUsername(), user.getEmail()}, int.class) == 1;
    }

    public boolean isPwdMatchName(User user){
        return jdbcTemplate.queryForObject(IS_MATCH_PWD_NAMD, new Object[]{user.getUsername(), user.getPassword()}, int.class) == 1;
    }

    public boolean isNormalUser(int user_id){
        return jdbcTemplate.queryForObject(IS_NORMAL_USER, new Object[]{user_id}, int.class) == 0;
    }

    public int getUserIdByName(String username){
        return jdbcTemplate.queryForObject(GET_ID_BY_NAME_AND_PWD, new Object[]{username}, int.class);
    }

    public User getUserByName(String username){
        User user = new User();
        jdbcTemplate.query(GET_USER_BY_NAME, new Object[]{username}, resultSet -> {
            user.setUser_id(resultSet.getInt("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
        });
        return user;
    }

    public void addFavor(int user_id, int info_id){
        jdbcTemplate.update(ADD_FAVOR, user_id, info_id);
    }

}
