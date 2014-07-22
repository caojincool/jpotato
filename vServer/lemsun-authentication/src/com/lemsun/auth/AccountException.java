package com.lemsun.auth;

import com.lemsun.core.auth.AuthenticationException;

/**
 * 有关账号的异常
 * User: Xudong
 * Date: 13-1-18
 * Time: 下午9:58
 */
public class AccountException extends AuthenticationException {


    /**
     * 账号不存在
     */
    public static final AccountException UnAccount = new AccountException("账号不存在", "003");

    /**
     * 密码错误, 或者密码位数格式不正确
     */
    public static final AccountException PasswordEx = new AccountException("密码错误, 或者密码位数格式不正确", "004");


    /**
     * 账号已经被锁定, 不能进行业务操作
     */
    public static final AccountException LockedAccount = new AccountException("账号已经被锁定, 不能进行业务操作", "005");

    /**
     * 帐号已经数据库中存在,请输入其他帐号
     */
    public static  final AccountException ExistAccount=new AccountException("帐号已经数据库中存在,请输入其他帐号","006");

    /**
     * 账号的重复登录
     */
    public static  final AccountException LoginAgain =new AccountException("账号重复登录","007");

    /**
     * 旧密码不一致!
     */
    public static final AccountException InconsistentPassword=new AccountException("旧密码不一致!","008");

    /**
     * 账户扩展信息ID已经存在,不能插入!
     */
    public static final AccountException AccountExpandByGuidRepeat=new AccountException("账户扩展信息ID已经存在,不能插入!","009");

    /**
     * 该账户的扩展信息不存在,不能更新
     */
    public static final  AccountException UnAccountExpand=new AccountException("该账户的扩展信息不存在,不能更新","010");

    /**
     * 扩展信息为空,不能保存
     */
    public static final AccountException AccountExpandIsNull=new AccountException("扩展信息为空,不能保存","011");

    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public AccountException(String msg, String code) {
        super(msg, code, AuthenticationException.AccountEx);
    }
}
