package com.dadagum.dao;

import com.dadagum.api.ActivityInfoDto;
import com.dadagum.bean.ActivityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InformationDao {

    static final String ACT_TABLE_NAME = "activity_info";

    private static final String ADD_ACTIVITY_SQL = "INSERT INTO " + ACT_TABLE_NAME + " VALUES(null, 2, ?, ?, ?, ?, ?)";

    private static final String GET_INFO_LIST_SQL = "SELECT org_name,introduction,name,start_time,end_time FROM " + ACT_TABLE_NAME + " NATURAL JOIN " + CategoryDao.TABLE_NAME + " WHERE category=?";

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

}
