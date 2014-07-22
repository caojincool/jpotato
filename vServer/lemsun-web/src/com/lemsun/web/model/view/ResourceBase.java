package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;


import java.util.Date;

/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 下午2:04
 */
public  class ResourceBase {
    private IResource resource;
    private IAccount account;
    protected IResource getResource() {
        return resource;
    }

    public ResourceBase(IResource resource,IAccount account){
        this.resource=resource;
        this.account=account;
    }
    public  String getShowName(){
      return  account.getShowName();
    }
    public String getPid() {
        return resource.getPid();
    }
    public String getBusinessCode(){
        return resource.getBusinessCode();
    }
    public String getName() {
        return resource.getName();
    }
    public String getCategory() {
        return resource.getCategory();
    }
    public String getCreateUser() {
        return resource.getCreateUser();
    }
    public Date getCreateTime() {
        return resource.getCreateTime();
    }
    public Date getUpdateTime() {
        return resource.getUpdateTime();
    }
    public String getParentPid() {
        return resource.getParentPid();
    }
    public boolean isSystem() {
        return resource.isSystem();
    }
    public String getRemark() {
        return resource.getRemark();
    }
    public int getState() {
        return resource.getState();
    }
}
