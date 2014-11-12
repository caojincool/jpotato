package com.dp.baobao.dao;

import com.dp.baobao.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by dpyang on 2014/10/5.
 */
@Repository
public class LoginLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog log){
        String sql="insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";
        Object[] objects={log.getUserId(),log.getIp(),log.getLoginDate()};
        jdbcTemplate.update(sql,objects);
    }
}
