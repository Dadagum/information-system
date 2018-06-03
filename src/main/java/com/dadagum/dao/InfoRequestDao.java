package com.dadagum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InfoRequestDao {

    static final String INFO_REQUEST_TABLE = "info_request";

    private static final String INSERT_REQUEST = "INSERT INTO " + INFO_REQUEST_TABLE + " VALUES(?, ?, waiting)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addRequest(int type_id, int info_id){
        jdbcTemplate.update(INSERT_REQUEST, type_id, info_id);
    }
}
