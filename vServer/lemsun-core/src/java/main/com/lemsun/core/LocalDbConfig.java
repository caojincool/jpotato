package com.lemsun.core;

/**
 * 本地数据的配置类型
 * User: 宗旭东
 * Date: 13-9-29
 * Time: 下午3:46
 */
public class LocalDbConfig {

    private String address;
    private String db;
    private String username;
    private String password;


    public LocalDbConfig(String address, String db, String username, String password) {
        this.address = address;
        this.db = db;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取数据库地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 获取数据库名称
     */
    public String getDb() {
        return db;
    }

    /**
     * 获取数据库用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取数据库密码
     */
    public String getPassword() {
        return password;
    }
}
