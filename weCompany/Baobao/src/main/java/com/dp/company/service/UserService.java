package com.dp.company.service;

import com.dp.company.dao.LoginLogDao;
import com.dp.company.dao.UserDao;
import com.dp.company.domain.LoginLog;
import com.dp.company.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dpyang on 2014/10/5.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName,String password){
        int matchCount=userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog=new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        return null;
    }
}
