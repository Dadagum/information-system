package com.dadagum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

    static final String INFO_REQUEST_TABLE = "info_addition_request";

    private static final String PASS_ACTIVITY = "UPDATE " + INFO_REQUEST_TABLE + " SET status = pass WHERE info_id = ? AND type_id = ?";

    private static final String DENY_ACTIVITY = "UPDATE " + INFO_REQUEST_TABLE + " SET status = pass WHERE info_id = ? AND type_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int passActivity(int type_id, int info_id){
        return jdbcTemplate.update(PASS_ACTIVITY, info_id, type_id);
    }

    public int denyActivity(int type_id, int info_id){
        return jdbcTemplate.update(DENY_ACTIVITY, info_id, type_id);
    }
}
