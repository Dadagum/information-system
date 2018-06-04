package com.dadagum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDao {

    private static final String CHECK_INFO_EXIST_SQL = "SELECT * FROM " + ActivityDao.ACTIVITY_TABLE + " WHERE info_id = ? AND type_id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * to check a specific information exists
     * @param info_id
     * @param type_id
     * @return
     */
    public boolean hasInfo(int info_id, int type_id){
        return jdbcTemplate.queryForObject(CHECK_INFO_EXIST_SQL, new Object[]{info_id, type_id}, int.class) == 1;
    }
}
