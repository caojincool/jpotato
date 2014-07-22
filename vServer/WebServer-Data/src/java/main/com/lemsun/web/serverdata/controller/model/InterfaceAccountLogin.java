package com.lemsun.web.serverdata.controller.model;

/**
 * 接口登录模型对象
 * User: 宗旭东
 * Date: 13-3-1
 * Time: 下午10:19
 */
public class InterfaceAccountLogin {

    private String username;
    private String password;
    private String plateformToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlateformToken() {
        return plateformToken;
    }

    public void setPlateformToken(String plateformToken) {
        this.plateformToken = plateformToken;
    }
}
