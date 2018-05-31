package com.dadagum.dao;

import com.dadagum.api.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private static final String TABLE_NAME = "user";

    private static final String INSERT_USER_SQL  = "INSERT INTO " + TABLE_NAME + " VALUES(null,?,?,?)";
    private static final String QUERY_USER_BY_NAME_PASSWORD = "SELECT count(*) FROM " + TABLE_NAME + "WHERE username=? AND password=?";

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
        jdbcTemplate.update(INSERT_USER_SQL, userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
    }

    /**
     * check if username match password when checking in
     * @param userDto
     * @return 0 if not match or not exist, 1 if match
     */
    public int loginCheck(UserDto userDto){
        return jdbcTemplate.queryForObject(QUERY_USER_BY_NAME_PASSWORD, new Object[]{userDto.getUsername(), userDto.getPassword()}, int.class);
    }


}
