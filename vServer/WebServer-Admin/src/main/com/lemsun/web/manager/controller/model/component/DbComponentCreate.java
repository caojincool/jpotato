package com.lemsun.web.manager.controller.model.component;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-10
 * Time: 下午2:41
 * 数据库连接资源创建, 或者修改
 */
public class DbComponentCreate {
    private String name ;
    private String pid;
    private String server;
    private String username;
    private String dbtype;
    private String password;
    private String dbName;
    private int maxActive = 10;
    private int maxIdea = 5;
    private int maxWait = 5000;

    /**
     * 组件名称
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源组件
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置资源组件
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取服务器地址
     */
    public String getServer() {
        return server;
    }

    /**
     * 设置服务器地址
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * 获取链接用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置链接用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取数据库产品类型
     */
    public String getDbtype() {
        return dbtype;
    }

    /**
     * 设置数据库产品类型
     */
    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    /**
     * 获取数据库密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置数据库密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取最大链接数
     */
    public int getMaxActive() {
        return maxActive;
    }

    /**
     * 设置最大链接数
     */
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * 获取等待时间
     */
    public int getMaxIdea() {
        return maxIdea;
    }

    /**
     * 设置等待时间
     */
    public void setMaxIdea(int maxIdea) {
        this.maxIdea = maxIdea;
    }

    /**
     * 设置等待
     */
    public int getMaxWait() {
        return maxWait;
    }

    /**
     * 获取等待
     */
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    /**
     * 获取数据库名称
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 设置数据库名称
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
