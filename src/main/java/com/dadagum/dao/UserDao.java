package com.dadagum.dao;

import com.dadagum.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    static final String USER_TABLE = "user";

    static final String PRIORITY_TABLE = "priority";

    private static final String INSERT_USER = "INSERT INTO " + USER_TABLE + " VALUES(null,?,?,?)";

    private static final String HAS_USER = "SELECT count(*) FROM " + USER_TABLE + "WHERE username=? AND password=?";

    private static final String UPDATE_PERSONAL_INFO = "UPDATE " + USER_TABLE + " SET password = ? WHERE user_id = ?";

    private static final String GET_PERSONAL_INFO = "SELECT username, email FROM " + USER_TABLE + " WHERE user_id = ?";

    private static final String GET_USER_PRIORITY = "SELECT identity from " + PRIORITY_TABLE + " WHERE uid = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * add a user into database
     * @param userDto
     */
    public void insertUser(UserDto userDto){
        jdbcTemplate.update(INSERT_USER, userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
    }

    /**
     * check if username match password when checking in
     * @param userDto
     * @return 0 if not match or not exist, 1 if match
     */
    public int loginCheck(UserDto userDto){
        return jdbcTemplate.queryForObject(HAS_USER, new Object[]{userDto.getUsername(), userDto.getPassword()}, int.class);
    }

    public void update(UserDto userDto, int user_id){
        jdbcTemplate.update(UPDATE_PERSONAL_INFO, userDto.getPassword(), user_id);
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
}
