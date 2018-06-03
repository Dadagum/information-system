package com.dadagum.dao;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    static final String USER_TABLE = "user";

    static final String FAVOR_TABLE = "favor";

    static final String PRIORITY_TABLE = "priority";

    private static final String INSERT_USER = "INSERT INTO " + USER_TABLE + " VALUES(null,?,?,?)";

    private static final String HAS_USER = "SELECT count(*) FROM " + USER_TABLE + "WHERE username=? AND password=?";

    private static final String UPDATE_PERSONAL_INFO = "UPDATE " + USER_TABLE + " SET password = ? WHERE user_id = ?";

    private static final String GET_PERSONAL_INFO = "SELECT username, email FROM " + USER_TABLE + " WHERE user_id = ?";

    private static final String GET_USER_PRIORITY = "SELECT identity from " + PRIORITY_TABLE + " WHERE uid = ?";

    private static final String GET_FAVOR_LIST = "SELECT org_name, introduction, name, start_time, end_time FROM " + FAVOR_TABLE + " NATURAL JOIN " + ActivityDao.ACTIVITY_TABLE + " WHERE user_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * add a user into database
     * @param user
     */
    public void insertUser(User user){
        jdbcTemplate.update(INSERT_USER, user.getUsername(), user.getEmail(), user.getPassword());
    }

    /**
     * check if username match password when checking in
     * @param user
     * @return 0 if not match or not exist, 1 if match
     */
    public int loginCheck(User user){
        return jdbcTemplate.queryForObject(HAS_USER, new Object[]{user.getUsername(), user.getPassword()}, int.class);
    }

    public void update(User user){
        jdbcTemplate.update(UPDATE_PERSONAL_INFO, user.getPassword(), user);
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
}
