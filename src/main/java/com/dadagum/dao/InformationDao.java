package com.dadagum.dao;

import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InformationDao {

    static final String ACT_TABLE_NAME = "activity_info";

    static final String TEAM_TABLE_NAME = "team_info";

    private static final String ADD_ACTIVITY_SQL = "INSERT INTO " + ACT_TABLE_NAME + " VALUES(null, 2, ?, ?, ?, ?, ?)";

    private static final String GET_INFO_LIST_SQL = "SELECT org_name,introduction,name,start_time,end_time FROM " + ACT_TABLE_NAME + " NATURAL JOIN " + CategoryDao.TABLE_NAME + " WHERE category=?";

    private static final String UPDATE_ACTIVITY_SQL = "UPDATE " + ACT_TABLE_NAME + " SET org_name = ?, introduction = ?, name = ?, start_time = ?, end_time = ? WHERE info_id = ?";

    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM " + ACT_TABLE_NAME + " WHERE info_id = ?";

    private static final String ADD_TEAM_REQUEST_SQL = "INSERT INTO " + TEAM_TABLE_NAME + " VALUES(null, 1, ?, ?, ?)";

    private static final String CHECK_INFO_EXIST_SQL = "SELECT * FROM " + ACT_TABLE_NAME + " WHERE info_id = ? AND type_id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * add a activity
     * @param activityInfoDto
     */
    public void addActivity(ActivityInfoDto activityInfoDto){
        jdbcTemplate.update(ADD_ACTIVITY_SQL, activityInfoDto.getOrg_name(), activityInfoDto.getIntroduction(), activityInfoDto.getName(), activityInfoDto.getStart_time(), activityInfoDto.getEnd_time());
    }

    //TODO
    /**
     * get specific category info list
     * @param category
     * @return
     */
    public List<?> getAllCategoryInfo(String category){
        List<ActivityInformation> result = new ArrayList<>();
        jdbcTemplate.query(GET_INFO_LIST_SQL, new Object[]{category}, resultSet -> {
            ActivityInformation activityInformation = new ActivityInformation();
            activityInformation.setName(resultSet.getString("name"));
            activityInformation.setOrg_name(resultSet.getString("org_name"));
            activityInformation.setIntroduction(resultSet.getString("introduction"));
            activityInformation.setStart_time(resultSet.getString("start_time"));
            activityInformation.setEnd_time(resultSet.getString("end_time"));
            result.add(activityInformation);
        });
        return result;
    }

    public int updateActivity(ActivityInformation info){
        return jdbcTemplate.update(UPDATE_ACTIVITY_SQL, info.getOrg_name(), info.getIntroduction(), info.getName(), info.getStart_time(), info.getEnd_time(), info.getInfo_id());
    }

    public int deleteActivity(int info_id){
        return jdbcTemplate.update(DELETE_ACTIVITY_SQL, info_id);
    }

    public void addTeamRequest(TeamRequest request){
        jdbcTemplate.update(ADD_TEAM_REQUEST_SQL, request.getTitle(), request.getIntroduction(), request.getUser_id());
    }

    public boolean hasInfo(int info_id, int type_id){
        return jdbcTemplate.queryForObject(CHECK_INFO_EXIST_SQL, new Object[]{info_id, type_id}, int.class) == 1;
    }
}
