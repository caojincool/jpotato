package com.dp.baobao.service.impl;

import com.dp.baobao.dao.LoginLogDao;
import com.dp.baobao.dao.UserDao;
import com.dp.baobao.domain.User;
import com.dp.baobao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dpyang on 2014/10/5.
 */
@Service
public class UserService implements IUserService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;

    @Autowired
    public UserService(UserDao userDao, LoginLogDao loginLogDao) {
        this.userDao = userDao;
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName,String password){
        int matchCount=userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    public void loginSuccess(User user){
        userDao.updateLoginInfo(user);
    }


}
