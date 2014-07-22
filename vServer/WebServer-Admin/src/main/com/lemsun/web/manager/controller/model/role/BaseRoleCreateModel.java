package com.lemsun.web.manager.controller.model.role;

import com.lemsun.auth.BaseRole;
import com.lemsun.auth.Permission;
import com.lemsun.auth.PermissionKey;
import com.lemsun.web.manager.controller.model.permissionNode.BasePermissionKey;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色创建模型,保存前台导航创建角色信息
 * User: dpyang
 * Date: 13-1-16
 * Time: 下午2:22
 */
public class BaseRoleCreateModel {

    private String name;
    private String describe;
    private List<BasePermission> permissions;


    /**
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色说明
     *
     * @return 角色说明
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置角色说明
     *
     * @param
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取角色权限
     *
     * @return 角色权限
     */
    public List<BasePermission> getPermissions() {
        return permissions;
    }

    /**
     * 设置角色权限
     *
     * @param
     */
    public void setPermissions(List<BasePermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * 创建基本的角色信息
     */
    public BaseRole createBaseRole(){
            BaseRole  baseRole=new BaseRole();
        baseRole.setName(getName());
        baseRole.setDescribe(getDescribe());
        return baseRole;
    }

    /**
     * 创建角色信息
     *
     * @return 角色信息
     */
    public BaseRole createRole() {
        BaseRole role = new BaseRole();
        role.setName(getName());
        role.setDescribe(getDescribe());
        List<PermissionKey> p=new ArrayList<>(permissions.size());

        for(BasePermission bp:permissions){
            Permission pd=Permission.getPermission(bp.getPermission());
            PermissionKey pk=new PermissionKey(pd,bp.getFullkey());
            p.add(pk);
        }
        //设置集合
        role.setPermissions(p);
        return role;
    }
}
