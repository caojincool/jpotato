package com.dp.baobao.service;

import com.dp.baobao.domain.User;

/**
 * Created by dpyang on 2014/11/12.
 */
public interface IUserService {

    boolean hasMatchUser(String userName,String password);

    User findUserByUserName(String userName);
}
