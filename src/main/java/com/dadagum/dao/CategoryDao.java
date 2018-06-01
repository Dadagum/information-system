package com.dadagum.dao;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CategoryDao {

    static final String TABLE_NAME = "info_type";

    private static final String INSERT_TYPE_SQL = "INSERT INTO " + TABLE_NAME + " VALUES(null,?)";

    private static final String GET_COUNT_SQL = "SELECT count(*) from " + TABLE_NAME + " WHERE category = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addType(Category category){
        jdbcTemplate.update(INSERT_TYPE_SQL, category.getName());
    }

    public int getCountOfSpecificCategory(String category){
        return jdbcTemplate.queryForObject(GET_COUNT_SQL, new Object[]{category}, int.class);
    }

}
