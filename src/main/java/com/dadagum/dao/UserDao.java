package com.dadagum.dao;

import com.dadagum.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    static final String USER_TABLE_NAME = "user";

    private static final String INSERT_USER_SQL  = "INSERT INTO " + USER_TABLE_NAME + " VALUES(null,?,?,?)";

    private static final String HAS_USER_SQL = "SELECT count(*) FROM " + USER_TABLE_NAME + "WHERE username=? AND password=?";

    private static final String UPDATE_PERSONAL_INFO_SQL = "UPDATE " + USER_TABLE_NAME + " SET password = ? WHERE user_id = ?";

    private static final String GET_PERSONAL_INFO_SQL = "SELECT username, email FROM " + USER_TABLE_NAME + " WHERE user_id = ?";

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
        return jdbcTemplate.queryForObject(HAS_USER_SQL, new Object[]{userDto.getUsername(), userDto.getPassword()}, int.class);
    }

    public void update(UserDto userDto, int user_id){
        jdbcTemplate.update(UPDATE_PERSONAL_INFO_SQL, userDto.getPassword(), user_id);
    }

    public UserDto getPersonalInfo(int user_id){
        final UserDto userDto = new UserDto();
        jdbcTemplate.query(GET_PERSONAL_INFO_SQL, new Object[]{user_id}, resultSet -> {
            userDto.setEmail(resultSet.getString("email"));
            userDto.setUsername(resultSet.getString("username"));
        });
        return userDto;
    }
}
