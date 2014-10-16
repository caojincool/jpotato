package com.dp.company.dao;

import com.dp.company.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dpyang on 2014/10/3.
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName,String password){
        String sql="select count(*) from t_user where user_name=? and password=?";
        return jdbcTemplate.queryForInt(sql,new Object[]{userName,password});
    }

    public User findUserByUserName(final String userName){
        String sql="select user_id,user_name,credits from t_user where user_name=?";
        final User user=new User();
        jdbcTemplate.query(sql,new Object[]{userName},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String sql="update t_user set last_visit=?,last_Ip=?,credits=? where user_id=?";

        jdbcTemplate.update(sql,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits()});
    }
}
