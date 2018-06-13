package com.dadagum.dao.impl;

import com.dadagum.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao{

    static final String INFO_REQUEST_TABLE = "info_addition_request";

    private static final String PASS_ACTIVITY = "UPDATE " + INFO_REQUEST_TABLE + " SET status = pass WHERE info_id = ?";

    private static final String DENY_ACTIVITY = "UPDATE " + INFO_REQUEST_TABLE + " SET status = pass WHERE info_id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean passActivity(int info_id) {
        return jdbcTemplate.update(PASS_ACTIVITY, info_id) == 1;
    }

    public boolean denyActivity(int info_id) {
        return jdbcTemplate.update(DENY_ACTIVITY, info_id) == 1;
    }
}
