package com.lemsun.web.manager.model;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-11-14
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class LoginModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String name;
    public String password;

}
