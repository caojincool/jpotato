package com.lemsun.auth.service;

import com.lemsun.auth.*;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IAccount;
import com.lemsun.core.auth.AuthenticationException;
import com.lemsun.core.member.IUserSession;
import com.lemsun.core.service.IAccountCoreService;
import org.springframework.data.domain.Page;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 账户信息服务接口
 * User: ssy
 * Date: 12-11-29
 * Time: 下午4:42
 * 账号信息
 */
public interface IAccountService extends IAccountCoreService {

    /**
     * 保存数据
     */
    void save(IAccount account) throws AccountException;

    /**
     * 更新给定用户的头像
     * @param account 账户信息
     * @param straem 头像流
     */
    void updateAvatar(IAccount account, InputStream straem);

    /**
     * 更新给定账户的头像
     * @param account 账户名称
     * @param stream 头像流
     */
    void updateAvatar(String account, InputStream stream) throws AccountException;




    /**
     * 获取一个在账号对象中的明文, 进行编码后的密码
     */
    String encodePassword(IAccount account) throws Exception;

    /**
     * 更新账户密码
     */
    void changePassword(IAccount account,String oldPassword, String newPassword) throws AccountException;

    /**
     * 更新账户密码
     * @param account 账户名称
     * @param oldPassowd 旧密码
     * @param newPassword 新密码
     */
    void changePassword(String account,String oldPassowd,String newPassword) throws AccountException;

    /**
     * 更新账户信息
     */
    void updateAccount(IAccount baseAccount) throws AuthenticationException;

    /**
     * 获取分页的账号数据列�
     */
    Page<IAccount> getPageData(AbstractPageRequest query);


    /**
     * 获取账户的的扩展信息
     * @param account
     * @return
     * @throws AccountException
     */
    public ExpandAccount getAccountExpand(IAccount account) throws AccountException;

    /**
     * 使用一个票据获取账号对�
     * @param token 票据
     * @return 账号对象
     */
    IAccount getByToken(String token);

    /**
     * 删除帐号
     * @param id  帐号
     */
    void deleteAccountById(String id) throws AccountException;

    /**
     * 执行一个用户的登录操作
     * @param account 账号
     * @param password 密码
     * @param ip D登录地址
     * @return  返回权限管理对象
     */
    Authentication doLogin(String account, String password, String ip) throws AuthenticationException;

    /**
     * 执行一个用户登� 如果登录用户已经具备Token代码. 就直接寻址已经登录的账� 并执行登录返回权限对�
     * @param token 登录标示
     * @return 账号对象
     * @throws AuthenticationException
     */
    AccountManager doLogin(IUserSession userSession, String token) throws AuthenticationException;

    /**
     * 执行账号登陆, 并返回账号管理对�
     * @param userSession 客户端会话对�
     * @param account 账号
     * @param password 账号密码
     * @return 用户管理
     * @throws AuthenticationException
     */
    AccountManager doLogin(IUserSession userSession, String account, String password) throws AuthenticationException;

    /**
     * 退出一个账号对�
     * @param account 账号
     */
    void Logout(String account);


    /**
     * 设置当前处理账户的管理对�
     * @param accountManager 账号管理对象
     */
    void setCurrentAccountManager(AccountManager accountManager);

    /**
     * 创建一个新的来宾账�
     * @return 来宾账号
     */
    IAccount createGuest();

    /**
     *  更新一个账户的权限信息
     *
     * @param account 账户信息
     * @param pks 权限集合
     */
    public void updateAccountPermission(IAccount account, Set<PermissionKey> pks);

    /**
     * 插入账户权限节点
     * @param account 账户
     * @param pks 权限节点
     */
    public void insertAccountPermissions(IAccount account,Set<PermissionKey> pks);

    /**
     * 返回一个账户的权限信息
     *
     * @param account 账户
     * @return 账户对应的权限信息
     */
    public List<PermissionKey> getAccountPermissions(IAccount account);

    /**
     * 更新账户扩展信息
     * @param expandAccounts 扩展信息集合
     */
    public void updateExpand(ExpandAccount expandAccounts) throws AccountException;

    /**
     * 扩展完整的账户信息
     * @param account 账户
     * @param expandAccounts 扩展信息(主要是联系方式)
     * @param avatar 头像
     * @param permsissions 权限集合
     */
    public void updateExpand(BaseAccount account,List<ExpandAccount> expandAccounts,InputStream avatar,Set<PermissionKey> permsissions) throws AccountException;

    /**
     * 根据账户编码获取账户详细信息
     * @param accountId 账户编码
     * @return 账户详细信息
     */
    public BaseAccount getAccountByAccountId(String accountId) throws AccountException;

    /**
     * 重置密码
     * @param accountId 账户编码
     *
     */
    public void resetPassword(String accountId) throws AccountException;

    /**
     * 通过账号获取账号下挂角色
     * @param accountId
     * @return
     * @throws AccountException
     */
    public List<BaseAccountRole> queryAccountByAccountId(String accountId) throws AccountException;

    /**
     *
     * @param accountRoles
     * @return
     */
    public List<BaseRole> queryAccountByRoleIds(List<BaseAccountRole> accountRoles);


    /**
     * 根据账号获取用户
     * @param account 账户
     * @return 账户详细信息
     */
    public BaseAccount getAccountByAccount(String account) throws AccountException;

    /**
     * 根据用户姓名获取用户列表
     * @param showName
     * @return
     * @throws AccountException
     */
    public List<BaseAccount> getAccountByShowName(String showName)throws AccountException;
}