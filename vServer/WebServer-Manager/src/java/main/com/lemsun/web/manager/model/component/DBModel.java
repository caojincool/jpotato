package com.lemsun.web.manager.model.component;

import com.lemsun.data.connection.DbCategory;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-14
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class DBModel {
    private String pid;
    private String name;
    private String category;
    private String dbCategory;
    private String connStr;
    private String server;
    private String username = "";
    private String password = "";
    private int maxActive = 10;
    private int maxIdea = 5;
    private int maxWait = 5000;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDbCategory() {
        return dbCategory;
    }

    public void setDbCategory(String dbCategory) {
        this.dbCategory = dbCategory;
    }

    public String getConnStr() {
        return connStr;
    }

    public void setConnStr(String connStr) {
        this.connStr = connStr;
    }

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

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        if(!StringUtils.isEmpty(maxActive)) {
            try{
                this.maxActive = Integer.parseInt(maxActive);
            }catch (Exception ex){}
        }
    }

    public int getMaxIdea() {
        return maxIdea;
    }

    public void setMaxIdea(String maxIdea) {
        if(!StringUtils.isEmpty(maxIdea)) {
            try{
                this.maxIdea = Integer.parseInt(maxIdea);
            }catch (Exception ex){}
        }
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        if(!StringUtils.isEmpty(maxWait)) {
            try{
                this.maxWait = Integer.parseInt(maxWait);
            }catch (Exception ex){}
        }
    }

    /**
     * 得到修改的数据库连接资源
     * @return
     * @throws Exception
     */
    public DbConfigResource getUpdateDateModel(IDbService dbService) throws Exception {

        if(StringUtils.isEmpty(getDbCategory()))
            throw new Exception("类型不能为空");
        if(StringUtils.isEmpty(getServer()))
            throw new Exception("连接字符串不能为空");
        DbConfigResource dbConfigResource= dbService.getDbconfigResource(getPid());
        if(dbConfigResource==null)
            throw new Exception("找不到要修改的资源");
        dbConfigResource.setDbCategory(DbCategory.getDbcategory(getDbCategory()));
        dbConfigResource.setServer(getServer());
        dbConfigResource.setUsername(getUsername());
        dbConfigResource.setPassword(getPassword());
        if(maxActive>0)
            dbConfigResource.setMaxActive(getMaxActive());
        if(maxIdea>0)
            dbConfigResource.setMaxIdea(getMaxIdea());
        if(maxWait>0)
            dbConfigResource.setMaxWait(getMaxWait());
        return dbConfigResource;
    }

    /**
     * 获取一个新建的数据连接资源
     * @return
     * @throws Exception
     */
    public DbConfigResource getDataModel() throws Exception {
        if(StringUtils.isEmpty(getName())||StringUtils.isEmpty(getCategory()))
            throw new Exception("名称和类别不能为空");
        DbConfigResource dbConfigResource=new DbConfigResource(name);
        dbConfigResource.setDbCategory(DbCategory.getDbcategory(getDbCategory()));
        //dbConfigResource.setConnStr(getConnStr());
        //dbConfigResource.setServer(getServer());
        dbConfigResource.setUsername(getUsername());
        dbConfigResource.setName(getName());
        dbConfigResource.setPassword(getPassword());
        if(maxActive>0)
            dbConfigResource.setMaxActive(getMaxActive());
        if(maxIdea>0)
            dbConfigResource.setMaxIdea(getMaxIdea());
        if(maxWait>0)
            dbConfigResource.setMaxWait(getMaxWait());
        return dbConfigResource;
    }
}
