package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.ldbc.DbCategory;

/**
 * User: 刘晓宝
 * Date: 13-11-21
 * Time: 上午9:27
 */
public class DbDbConfigItemView extends ResourceBase{
    private DbConfigResource resource=(DbConfigResource)getResource();
    public DbDbConfigItemView(IResource resource, IAccount account) {
        super(resource, account);
    }

    public String getUsername() {
        return resource.getUsername();
    }

    public String getPassword() {
        return resource.getPassword();
    }

    public int getMaxActive() {
        return resource.getMaxActive();
    }

    public int getMaxIdea() {
        return resource.getMaxIdea();
    }

    public int getMaxWait() {
        return resource.getMaxWait();
    }

    public boolean isDefaultDb() {
        return resource.isDefaultDb();
    }

    public DbCategory getDbCategory() {
        return resource.getDbCategory();
    }

    public String getServer() {
        return resource.getServer();
    }

    public String getDbName() {
        return resource.getDbName();
    }
}
