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

    private final String INSERT="INSERT INTO t_forum(id,name,nameEn,isEnabled)" +
            "values(?,?,?,?)";
    private final String UPDATE="UPDATE t_forum set name=?,nameEn=?,isEnabled=? where id=?";
    private final String GETFORUM="SELECT id,name,nameEn,isEnabled FROM t_forum WHERE id=?";
    private final String DELETE="DELETE from t_forum where id=?";
    private final String FORUMPAGE="";

    public void insertForum(final Forum forum){

        jdbcTemplate.update(INSERT,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,forum.getId().toString());
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

                forum.setId(UUID.fromString(rs.getString("id")));
                forum.setName(rs.getString("name"));
                forum.setNameEn(rs.getString("nameEn"));
                forum.setEnabled(rs.getBoolean("isEnabled"));
                return forum;
            }
        });
    }

    public void deleteForum(UUID id){
       jdbcTemplate.update(DELETE,new Object[]{id});
    }

    public void deleteForum(List<UUID> uuidList){

        List<String> sqls=new ArrayList<String>(uuidList.size());



    }
}
