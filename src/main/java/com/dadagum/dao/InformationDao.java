package com.dadagum.dao;

import com.dadagum.bean.ActivityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InformationDao {

    private static final String GET_INFO_LIST_SQL = "SELECT org_name,introduction,name,start_time,end_time FROM " + ActivityDao.ACT_TABLE_NAME + " NATURAL JOIN " + CategoryDao.TABLE_NAME + " WHERE category=?";

    private static final String CHECK_INFO_EXIST_SQL = "SELECT * FROM " + ActivityDao.ACT_TABLE_NAME + " WHERE info_id = ? AND type_id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

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


    public boolean hasInfo(int info_id, int type_id){
        return jdbcTemplate.queryForObject(CHECK_INFO_EXIST_SQL, new Object[]{info_id, type_id}, int.class) == 1;
    }
}
