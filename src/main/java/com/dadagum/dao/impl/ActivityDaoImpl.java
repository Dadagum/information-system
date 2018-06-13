package com.dadagum.dao.impl;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.ActivityDao;
import com.dadagum.dto.ActivityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityDaoImpl implements ActivityDao{

    static final String ACTIVITY_TABLE = "activity_info";

    private static final String UPDATE_ACTIVITY = "UPDATE " + ACTIVITY_TABLE + " SET org_name = ?, introduction = ?, name = ?, start_time = ?, end_time = ? WHERE info_id = ? AND user_id = ?";

    private static final String ADD_ACTIVITY = "INSERT INTO " + ACTIVITY_TABLE + " VALUES(null, 2, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_ACTIVITY = "DELETE FROM " + ACTIVITY_TABLE + " WHERE info_id = ?";

    private static final String GET_PASS_INFO_LIST = "SELECT org_name, introduction, name, start_time, end_time FROM " + ACTIVITY_TABLE + " NATURAL JOIN " + InformationDaoImpl.INFO_REQUEST_TABLE + " WHERE status = 'pass'";

    private static final String GET_SINGLE_INFO = "SELECT org_name, introduction, name, start_time, end_time FROM " + ACTIVITY_TABLE + " NATURAL JOIN " + InformationDaoImpl.INFO_REQUEST_TABLE + " WHERE status = 'pass' AND info_id = ?";

    private static final String HAS_ACTIVITY = "SELECT count(*) FROM " + ACTIVITY_TABLE + " WHERE info_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean updateActivity(ActivityInformation info){
        return jdbcTemplate.update(UPDATE_ACTIVITY, info.getOrg_name(), info.getIntroduction(), info.getName(), info.getStart_time(), info.getEnd_time(), info.getInfo_id(), info.getUser_id(), info.getUser_id()) == 1;
    }

    public boolean deleteActivity(int info_id){
        return jdbcTemplate.update(DELETE_ACTIVITY, info_id) == 1;
    }

    public List<ActivityInfoDto> getPassInfoList(){
        List<ActivityInfoDto> result = new ArrayList<>();
        jdbcTemplate.query(GET_PASS_INFO_LIST, resultSet -> {
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

    public ActivityInfoDto getSpecificInfo(int info_id){
        final ActivityInfoDto result = new ActivityInfoDto();
        jdbcTemplate.query(GET_SINGLE_INFO, new Object[]{info_id}, (resultSet) -> {
            result.setOrg_name(resultSet.getString("org_name"));
            result.setIntroduction(resultSet.getString("introduction"));
            result.setName(resultSet.getString("name"));
            result.setStart_time(resultSet.getString("start_time"));
            result.setEnd_time(resultSet.getString("end_time"));
            result.setInfo_id(resultSet.getInt("info_id"));
            result.setType_id(resultSet.getInt("type_id"));
        });
        return result;
    }

    @Override
    public boolean hasActivivy(int info_id) {
        return jdbcTemplate.queryForObject(HAS_ACTIVITY,new Object[]{info_id}, int.class) != 0;
    }

    public void addActivity(ActivityInformation activity){
        jdbcTemplate.update(ADD_ACTIVITY, activity.getOrg_name(), activity.getIntroduction(), activity.getName(), activity.getStart_time(), activity.getEnd_time(), activity.getUser_id());
    }


}
