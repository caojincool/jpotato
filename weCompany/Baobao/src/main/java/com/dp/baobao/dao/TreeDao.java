package com.dp.baobao.dao;

import com.dp.baobao.domain.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dpyang on 2014/11/22.
 */
@Repository
public class TreeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(final Tree tree) throws Exception {
        String sql="insert into t_km(_id,_code,_name,_parent)values(?,?,?,?)";
        if (tree.getId()==tree.getParent().getId())
            throw new Exception("该节点不能是自己的父节点");
        int result=jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,"");
                preparedStatement.setString(2,tree.getCode());
                preparedStatement.setString(3,tree.getName());
                preparedStatement.setString(4, StringUtils.isEmpty(tree.getParent().getId())?"":tree.getParent().getId());
            }
        });

        return result;
    }

    public Tree get(final String id){
        String sql="select * from t_km where _id=?";
        final Tree tree=new Tree();
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                tree.setId(id);
                tree.setName(resultSet.getString("_name"));
                tree.setCode(resultSet.getString("_code"));
                if (!StringUtils.isEmpty(resultSet.getString("_parent")))
                    tree.setParent(get(resultSet.getString("_parent")));
                tree.setChildren(loadChildren(id));
            }
        });
        return tree;
    }

    public List<Tree> loadChildren(final String id){
        String sql="select * from t_km where _parent=?";

        return jdbcTemplate.query(sql,new Object[]{id},new RowMapper<Tree>() {
            @Override
            public Tree mapRow(ResultSet resultSet, int i) throws SQLException {
                Tree tree=new Tree();
                tree.setName(resultSet.getString("_name"));
                tree.setCode(resultSet.getString("_code"));
                tree.setId(resultSet.getString("_id"));
                tree.setChildren(loadChildren(tree.getId()));
                return tree;
            }
        });
    }



    public int update(final Tree tree) throws Exception {
        if(tree.getId()==null)
            throw new Exception("节点编码是空的，不能修改！");
        if (tree.getId()==tree.getParent().getId())
            throw new Exception("该节点不能是自己的父节点");


        String sql="update t_km set _code=?,_name=?,_parent=? where _id=?";

        int result=jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,tree.getCode());
                preparedStatement.setString(2,tree.getName());
                preparedStatement.setString(3,tree.getParent().getId());
                preparedStatement.setString(4,tree.getId());
            }
        });

        return result;
    }


}
