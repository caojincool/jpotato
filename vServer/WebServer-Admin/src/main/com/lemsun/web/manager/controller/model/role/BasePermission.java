package com.lemsun.web.manager.controller.model.role;

import com.lemsun.auth.*;

/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-1-28
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public class BasePermission {
    private String fullkey;
    private int permission;

    public String getFullkey() {
        return fullkey;
    }

    public void setFullkey(String fullkey) {
        this.fullkey = fullkey;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    /**
     * 创建权限键值
     * @return
     */
    public PermissionKey createPermissionKey(){

        PermissionKey pk=new PermissionKey(com.lemsun.auth.Permission.getPermission(permission),fullkey);
        return pk;
    }
}
