package com.lemsun.core.auth;

import com.lemsun.core.LemsunException;

/**
 * 权限异常
 * User: Xudong
 * Date: 13-1-17
 * Time: 下午3:57
 */
public class AuthenticationException extends LemsunException {


    /**
     * 账号异常
     */
    public static final AuthenticationException AccountEx = new AuthenticationException("账号异常", "001");

    /**
     * 角色异常
     */
    public static final AuthenticationException RoleEx=new AuthenticationException("角色异常","002");

    /**
     * 权限异常
     */
    public static final AuthenticationException PermissionNodeEx=new AuthenticationException("权限异常","003");
    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public AuthenticationException(String msg, String code) {
        super(msg, code, LemsunException.AuthException);
    }



    /**
     * 构造子集异常
     * @param msg
     * @param code
     * @param auEx
     */
    public AuthenticationException(String msg, String code, AuthenticationException auEx) {
        super(msg, code, auEx);
    }
}
