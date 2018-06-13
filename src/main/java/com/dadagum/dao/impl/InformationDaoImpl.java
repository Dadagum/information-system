package com.dadagum.dao.impl;

import com.dadagum.dao.InformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDaoImpl implements InformationDao{

    private static final String CHECK_INFO_EXIST_SQL = "SELECT * FROM " + ActivityDaoImpl.ACTIVITY_TABLE + " WHERE info_id = ? AND type_id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String INFO_REQUEST_TABLE = "info_addtion_request";

    private static final String INSERT_REQUEST = "INSERT INTO " + INFO_REQUEST_TABLE + " VALUES(?, ?, waiting)";

    public void addRequest(int type_id, int info_id){
        jdbcTemplate.update(INSERT_REQUEST, type_id, info_id);
    }

    public boolean hasInfo(int info_id, int type_id){
        return jdbcTemplate.queryForObject(CHECK_INFO_EXIST_SQL, new Object[]{info_id, type_id}, int.class) == 1;
    }
}
