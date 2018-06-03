package com.dadagum.dao;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dto.ActivityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDao {

    static final String ACT_TABLE_NAME = "activity_info";

    private static final String UPDATE_ACTIVITY_SQL = "UPDATE " + ACT_TABLE_NAME + " SET org_name = ?, introduction = ?, name = ?, start_time = ?, end_time = ? WHERE info_id = ?";

    private static final String ADD_ACTIVITY_SQL = "INSERT INTO " + ACT_TABLE_NAME + " VALUES(null, 2, ?, ?, ?, ?, ?)";

    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM " + ACT_TABLE_NAME + " WHERE info_id = ?";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateActivity(ActivityInformation info){
        return jdbcTemplate.update(UPDATE_ACTIVITY_SQL, info.getOrg_name(), info.getIntroduction(), info.getName(), info.getStart_time(), info.getEnd_time(), info.getInfo_id());
    }

    public int deleteActivity(int info_id){
        return jdbcTemplate.update(DELETE_ACTIVITY_SQL, info_id);
    }

    /**
     * add a activity
     * @param activityInfoDto
     */
    public void addActivity(ActivityInfoDto activityInfoDto){
        jdbcTemplate.update(ADD_ACTIVITY_SQL, activityInfoDto.getOrg_name(), activityInfoDto.getIntroduction(), activityInfoDto.getName(), activityInfoDto.getStart_time(), activityInfoDto.getEnd_time());
    }

}
