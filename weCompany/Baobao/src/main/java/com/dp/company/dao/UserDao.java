package com.dp.company.dao;

import com.dp.company.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by dpyang on 2014/10/3.
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String loginAccount,String password){
        String sql="select count(*) from t_user where _loginAccount=? and _password=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{loginAccount,password},Integer.class);
    }

    public User findUserByUserName(final String userName){
        String sql="select _id,_showName from t_user where _loginAccount=?";
        final User user=new User();
        jdbcTemplate.query(sql,new Object[]{userName},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(UUID.fromString(resultSet.getString("_id")));
                user.setLoginAccount(userName);
                user.setShowName(resultSet.getString("_showName"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String sql="update t_user set _last_visit=?,_last_Ip=? where _id=?";

        jdbcTemplate.update(sql,new Object[]{user.getLastVisit(),user.getLastIp(),user.getId()});
    }
}
