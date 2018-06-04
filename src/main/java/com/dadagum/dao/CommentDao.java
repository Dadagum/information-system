package com.dadagum.dao;

import com.dadagum.bean.Comment;
import com.dadagum.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDao {

    static final String COM_TABLE_NAME = "comment";

    private static final String GET_COUNT_BY_ID = "SELECT count(*) FROM " + COM_TABLE_NAME + " WHERE comment_id = ?";

    private static final String INSERT_COMMENT_SQL = "INSERT INTO " + COM_TABLE_NAME + " VALUES(?, ?, ?, null, ?, ?, ?)";

    private static final String DELETE_COMMENT_SQL = "DELETE FROM " + COM_TABLE_NAME + " WHERE comment_id =?";

    private static final String GET_COMMENT_LIST = "SELECT username, type_id, info_id, parent_id, comment, create_time FROM " + COM_TABLE_NAME + " NATURAL JOIN " + UserDao.USER_TABLE + " WHERE type_id = ? AND info_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasComment(int comment_id){
        return jdbcTemplate.queryForObject(GET_COUNT_BY_ID, new Object[]{comment_id}, int.class) == 1;
    }

    public void addComment(Comment comment){
        jdbcTemplate.update(INSERT_COMMENT_SQL, comment.getUser_id(), comment.getType_id(), comment.getInfo_id(), comment.getParent_id(), comment.getComment(), comment.getCreate_time());
    }

    public boolean deleteComment(int comment_id){
        return jdbcTemplate.update(DELETE_COMMENT_SQL, comment_id) != 0;
    }

    //TODO
    public List<CommentDto> getSpecificComment(int type_id, int info_id){
        List<CommentDto> result = new ArrayList<>();
        jdbcTemplate.query(GET_COMMENT_LIST, new Object[]{type_id, info_id}, resultSet -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setUsername(resultSet.getString("username"));
            commentDto.setType_id(resultSet.getInt("type_id"));
            commentDto.setInfo_id(resultSet.getInt("info_id"));
            commentDto.setParent_id(resultSet.getInt("parent_id"));
            commentDto.setComment(resultSet.getString("comment"));
            commentDto.setCreate_time(resultSet.getString("create_time"));
            result.add(commentDto);
        });
        return result;
    }
}
