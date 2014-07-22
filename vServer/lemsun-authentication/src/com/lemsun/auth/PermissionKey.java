package com.lemsun.auth;

/**
 * 权限节点对象. 权限节点按照树形结构组织. 一个父节点的权限分配可以覆盖子节点的默认权限
 * User: Xudong
 * Date: 12-11-23
 * Time: 上午10:40
 */
public class PermissionKey {

    private Permission permission;
    private String key;

    /**
     * 构造一个权限节点配置信息
     * @param permission 权限配置
     * @param key 建值
     */
    public PermissionKey(Permission permission, String key) {
        this.permission = permission;
        this.key = key;
    }

    /**
     * 获取当前权限节点所设置的权限
     */
    public Permission getPermission() {
        return permission;
    }

    /**
     * 获取权限节点的建值
     */
    public String getKey() {
        return key;
    }


}
