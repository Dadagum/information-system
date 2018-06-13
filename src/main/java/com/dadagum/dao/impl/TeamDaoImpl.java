package com.dadagum.dao.impl;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dao.TeamDao;
import com.dadagum.dto.TeamRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamDaoImpl implements TeamDao{

    static final String TEAM_TABLE = "team_info";

    private static final String ADD_TEAM_REQUEST = "INSERT INTO " + TEAM_TABLE + " VALUES(null, 1, ?, ?, ?)";

    private static final String GET_TEAM_REQUEST_LIST = "SELECT info_id, introduction, title, username, email FROM " + TEAM_TABLE + " NATURAL JOIN " + UserDaoImpl.USER_TABLE;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addTeamRequest(TeamRequest request){
        jdbcTemplate.update(ADD_TEAM_REQUEST, request.getTitle(), request.getIntroduction(), request.getUser_id());
    }

    public List<TeamRequestDto> getTeamRequestList(){
        List<TeamRequestDto> result = new ArrayList<>();
        jdbcTemplate.query(GET_TEAM_REQUEST_LIST, resultSet -> {
            TeamRequestDto tmp = new TeamRequestDto();
            tmp.setInfo_id(resultSet.getInt("info_id"));
            tmp.setIntroduction(resultSet.getString("introduction"));
            tmp.setTitle(resultSet.getString("title"));
            tmp.setUsername(resultSet.getString("username"));
            tmp.setEmail(resultSet.getString("email"));
            result.add(tmp);
        });
        return result;
    }
}
