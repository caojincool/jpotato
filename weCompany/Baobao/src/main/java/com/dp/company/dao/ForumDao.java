package com.dp.company.dao;

import com.dp.company.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
@Repository
public class ForumDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT="INSERT INTO t_forum(_id,_name,_nameEn,_isEnabled)" +
            "values(?,?,?,?)";
    private final String UPDATE="UPDATE t_forum set _name=?,_nameEn=?,_isEnabled=? where _id=?";
    private final String GETFORUM="SELECT _id,_name,_nameEn,_isEnabled FROM t_forum WHERE _id=?";
    private final String DELETE="DELETE from t_forum where _id=?";
    private final String FORUMPAGE="";

    public void insert(final Forum forum){

        jdbcTemplate.update(INSERT,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,forum.getId()==null?"":forum.getId().toString());
                ps.setString(2,forum.getName());
                ps.setString(3,forum.getNameEn());
                ps.setBoolean(4,forum.isEnabled());
            }
        });
    }

    public void updateForum(final Forum forum){

        jdbcTemplate.update(UPDATE,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,forum.getName());
                ps.setString(2,forum.getNameEn());
                ps.setBoolean(3,forum.isEnabled());
                ps.setString(4,forum.getId().toString());
            }
        });
    }

    public Forum getForum(String id){

        return jdbcTemplate.queryForObject(GETFORUM,new Object[]{id},new RowMapper<Forum>() {
            @Override
            public Forum mapRow(ResultSet rs, int i) throws SQLException {
                Forum forum=new Forum();

                forum.setId(UUID.fromString(rs.getString("_id")));
                forum.setName(rs.getString("_name"));
                forum.setNameEn(rs.getString("_nameEn"));
                forum.setEnabled(rs.getBoolean("_isEnabled"));
                return forum;
            }
        });
    }

    public void deleteForum(UUID id){
       jdbcTemplate.update(DELETE,new Object[]{id});
    }

    public List<Forum> queryAll(){
        String sql="select * from t_forum";

        return jdbcTemplate.query(sql,new RowMapper<Forum>() {
            @Override
            public Forum mapRow(ResultSet resultSet, int i) throws SQLException {
                Forum forum=new Forum();
                forum.setId(UUID.fromString(resultSet.getString("_id")));
                forum.setName(resultSet.getString("_name"));
                forum.setNameEn(resultSet.getString("_nameEn"));
                forum.setEnabled(resultSet.getBoolean("_isEnabled"));
                return forum;
            }
        });
    }
}
