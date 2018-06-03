package com.dadagum.dao;

import com.dadagum.bean.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDao {

    static final String TEAM_TABLE_NAME = "team_info";

    private static final String ADD_TEAM_REQUEST_SQL = "INSERT INTO " + TEAM_TABLE_NAME + " VALUES(null, 1, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addTeamRequest(TeamRequest request){
        jdbcTemplate.update(ADD_TEAM_REQUEST_SQL, request.getTitle(), request.getIntroduction(), request.getUser_id());
    }
}
