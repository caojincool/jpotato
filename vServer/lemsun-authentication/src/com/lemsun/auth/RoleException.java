package com.lemsun.auth;

import com.lemsun.core.auth.AuthenticationException;

/**
 * 有关角色的异常
 * User: dpyang
 * Date: 13-1-19
 * Time: 上午9:21
 */
public class RoleException extends AuthenticationException {

    /**
     * 构造新的异常
     * @param msg 异常信息
     * @param code 异常的代码
     */
    public RoleException(String msg, String code) {
        super(msg, code, AuthenticationException.RoleEx);
    }

    /**
     * 角色已经在数据库中存在,不能插入重复的角色名称
     */
    public static final  RoleException ExistRole=new RoleException("角色已经在数据库中存在,不能插入重复的角色名称","001");

    /**
     * 角色名称为空,不能查询该角色的信息
     */
    public static  final RoleException RoleisNull=new RoleException("角色名称为空,不能查询该角色的信息","002");

    /**
     * 加入角色组的账户不能为空!
     * 主要用于验证创建角色时候
     */
    public static final RoleException BaseAccountRoleAccountIsNull=new RoleException("加入角色组的账户不能为空!","003");

}
