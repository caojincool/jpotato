package com.lemsun.core;

/**
 * 组件权限
 * User: dp
 * Date: 13-8-30
 * Time: 下午3:54
 */
public class ResourcePermission {

    private String name;
    private int type;
    private Permission permission;

    /**
     * 账户类型
     */
    public static final int ACCOUNT=1;

    /**
     * 角色类型
     */
    public static final int ROLE=2;

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 类型
     * 角色或者账户
     */
    public int getType() {
        return type;
    }

    /**
     * 类型
     * 角色或者账户
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 权限
     */
    public Permission getPermission() {
        return permission;
    }

    /**
     * 权限
     */
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString(){
        return "名称："+name+"  类型："+type+"  权限："+permission;
    }
}
