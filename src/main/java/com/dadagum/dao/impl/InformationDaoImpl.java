package com.dadagum.dao.impl;

import com.dadagum.dao.InformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDaoImpl implements InformationDao{

    static final String INFO_REQUEST_TABLE = "info_addition_request";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_REQUEST = "INSERT INTO " + INFO_REQUEST_TABLE + " VALUES(?, ?, 'waiting')";

    private static final String DELETE_REQUEST = "DELETE FROM " + INFO_REQUEST_TABLE + " WHERE info_id = ? AND type_id = ?";

    public void addRequest(int type_id, int info_id){
        jdbcTemplate.update(INSERT_REQUEST, info_id, type_id);
    }

    @Override
    public void deleteRequest(int type_id, int info_id) {
        jdbcTemplate.update(DELETE_REQUEST, info_id, type_id);
    }

    public boolean hasInfo(int info_id, int type_id, String table){
        String CHECK_INFO_EXIST_SQL = "SELECT count(*) FROM " + table + " WHERE info_id = ? AND type_id = ?";
        return jdbcTemplate.queryForObject(CHECK_INFO_EXIST_SQL, new Object[]{info_id, type_id}, int.class) == 1;
    }
}
