package com.lemsun.web.manager.controller.model.permissionNode;

import com.lemsun.auth.Permission;
import com.lemsun.auth.PermissionNodeExcetion;

/**
 * 前台封装的权限键值对
 * User: dpyang
 * Date: 13-1-28
 * Time: 上午11:14
 */
public class BasePermissionKey  {

    private String key;
    private Permission permission;

    public BasePermissionKey(){
    }

    /**
     * 设置权限节点
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 设置权限对应的枚举
     */
    public void setPermission(String p) throws PermissionNodeExcetion{

        if (permission.equals("未知"))
            this.permission = Permission.Unkonw;
        else if(permission.equals("允许"))
            this.permission=Permission.Allow;
        else if(permission.equals("拒绝"))
            this.permission=Permission.Deny;
        else if(permission.equals("隐藏"))
            this.permission=Permission.Hiddle;
        else
            throw  PermissionNodeExcetion.PIdisNull;

    }

    /**
     * 获取权限键值
     * @return
     */
    public String getKey() {
        return key;
    }
}
