package com.lemsun.auth;

import com.lemsun.core.auth.AuthenticationException;

/**
 * 有关权限的异常
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class PermissionNodeExcetion extends AuthenticationException {

    public PermissionNodeExcetion(String msg, String code) {
        super(msg, code, AuthenticationException.PermissionNodeEx);
    }

    /**
     * 数据库权限ID已经存在,不能插入数据库中重复的权限ID
     */
    public static final PermissionNodeExcetion PIdExists = new PermissionNodeExcetion("数据库权限ID已经存在!", "001");


    /**
     * 权限名称不能为空
     */
    public static final PermissionNodeExcetion PNameisNull = new PermissionNodeExcetion("权限名称不能为空!", "002");

    /**
     * 权限在数据库中不存在!
     */
    public static final PermissionNodeExcetion PIdisNull = new PermissionNodeExcetion("权限在数据库中不存在!", "003");

    /**
     * 权限Key不能为空!
     */
    public static final PermissionNodeExcetion PKeyIsNull = new PermissionNodeExcetion("权限Key不能为空!", "004");

    /**
     * 权限父节点不能为空!
     */
    public static final PermissionNodeExcetion PParentIsNull=new PermissionNodeExcetion("权限父节点不能为空!","005");

    /**
     * 根节点不能删除!
     */
    public static final PermissionNodeExcetion PRootDel=new PermissionNodeExcetion("根节点不能被删除!","006");

    /**
     * 权限节点已经存在!
     */
    public static  final PermissionNodeExcetion PKeyIsExists=new PermissionNodeExcetion("权限节点已经存在!","007");

}
