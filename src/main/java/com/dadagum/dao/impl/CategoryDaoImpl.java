package com.dadagum.dao.impl;

import com.dadagum.bean.Category;
import com.dadagum.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    static final String TABLE_NAME = "info_type";

    private static final String INSERT_TYPE_SQL = "INSERT INTO " + TABLE_NAME + " VALUES(null,?)";

    private static final String GET_COUNT_BY_NAME_SQL = "SELECT count(*) from " + TABLE_NAME + " WHERE category = ?";

    private static final String GET_COUNT_BY_ID_SQL = "SELECT count(*) from " + TABLE_NAME + " WHERE type_id = ?";

    private static final String GET_ID_BY_NAME_SQL = "SELECT type_id FROM " + TABLE_NAME + " WHERE category = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addType(Category category){
        jdbcTemplate.update(INSERT_TYPE_SQL, category.getName());
    }

    public boolean hasCategory(String category){
        return jdbcTemplate.queryForObject(GET_COUNT_BY_NAME_SQL, new Object[]{category}, int.class) == 1;
    }

    public boolean hasCategory(int type_id){
        return jdbcTemplate.queryForObject(GET_COUNT_BY_ID_SQL, new Object[]{type_id}, int.class) == 1;
    }

    public int getIdByCategory(String category){
        return jdbcTemplate.queryForObject(GET_ID_BY_NAME_SQL, new Object[]{category}, int.class);
    }

}
