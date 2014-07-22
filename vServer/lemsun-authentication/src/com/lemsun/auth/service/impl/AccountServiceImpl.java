package com.lemsun.auth.service.impl;

import com.lemsun.auth.*;
import com.lemsun.auth.repository.AccountRepository;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IAccount;
import com.lemsun.core.auth.AuthenticationException;
import com.lemsun.core.member.IUserSession;
import com.lemsun.core.service.IAccountManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zonxudong
 * Date: 12-11-30
 * Time: 上午8:54
 * 账号服务
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository repository;
    private HashMap<String, AccountManager> accountManagerHashMap = new HashMap<>(500);
    private HashMap<String, String> tokenMap = new HashMap<>(500);
    private final static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private static ThreadLocal<AccountManager> threadLocal = new ThreadLocal<>();

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * 保存新的账号. 如果账号已经有了ID, 抛出异常
     */
    @Override
    public void save(IAccount account) throws AccountException {

        if (repository.getAccountByAccount(account.getAccount()) != null) throw AccountException.ExistAccount;

        if (StringUtils.isEmpty(account.getPassword())) throw AccountException.PasswordEx;

        if(account instanceof BaseAccount)
        {
            BaseAccount acc = (BaseAccount) account;

            acc.setPassword(encodePassword(account));//加密
            repository.save(acc);
        }
        else
        {
            throw AccountException.ExistAccount;
        }

    }

    /**
     * 更新账户的权限信息
     *
     * @param account 账户信息
     * @param pks     权限信息
     */
    public void updateAccountPermission(IAccount account, Set<PermissionKey> pks) {
        repository.updateAccountPermission(account, pks);
    }

    /**
     * 插入账户权限节点
     *
     * @param account 账户
     * @param pks     权限节点
     */
    @Override
    public void insertAccountPermissions(IAccount account, Set<PermissionKey> pks) {
        if (account != null && pks.size() > 0)
            repository.insertAccountPermissions(account, pks);
    }

    /**
     * 返回一个账户的权限信息
     *
     * @param account 账户
     * @return 账户对应的权限信息
     */
    @Override
    public List<PermissionKey> getAccountPermissions(IAccount account) {

        return repository.getAccountPermssion(account);
    }

    /**
     * 更新账户扩展信息
     *
     * @param expandAccounts 扩展信息集合
     */
    @Override
    public void updateExpand(ExpandAccount expandAccounts) throws AccountException {
            repository.updateAccountExpand(expandAccounts);
    }

    /**
     * 扩展完整的账户信息
     *
     * @param account        账户
     * @param expandAccounts 扩展信息(主要是联系方式)
     * @param avatar         头像
     * @param permsissions   权限集合
     */
    @Override
    public void updateExpand(BaseAccount account, List<ExpandAccount> expandAccounts, InputStream avatar, Set<PermissionKey> permsissions) throws AccountException {

        //基本判断
        if (account == null || StringUtils.isEmpty(account.getAccount())) throw AccountException.UnAccount;

        //这里是不是应该用事务提交.
        //更新基本账户信息
        repository.updateAccount(account);

        //更新扩展信息
        for (ExpandAccount ea : expandAccounts) {
            repository.updateAccountExpand(ea);
        }

        //更新头像
        if (avatar != null)
            repository.updateAvatarData(account, avatar);

        //更新权限
        repository.updateAccountPermission(account, permsissions);
    }

    /**
     * 根据账户编码获取账户详细信息
     *
     * @param accountId 账户编码
     * @return 账户详细信息
     */
    @Override
    public BaseAccount getAccountByAccountId(String accountId) throws AccountException {
       if (StringUtils.isEmpty(accountId))
           throw AccountException.UnAccount;
       return repository.getAccountByAccountId(accountId);
    }

    /**
     * 根据账户信息更新头像
     *
     * @param account 账户信息
     * @param straem  头像流
     */
    @Override
    public void updateAvatar(IAccount account, InputStream straem) {
        repository.updateAvatarData(account, straem);
    }

    /**
     * 根据账户名称更新账户信息或者保存默认头像
     *
     * @param account 账户名称
     * @param stream  头像流
     */
    @Override
    public void updateAvatar(String account, InputStream stream) throws AccountException {

        if ("default".equals(account)) {
            setdefaultAvatar(stream);
            return;
        }
        IAccount acc = getByAccount(account);

        if (acc == null) throw AccountException.UnAccount;
        updateAvatar(acc, stream);
    }

    /**
     * 设置默认头像
     *
     * @param stream 头像流
     */
    private void setdefaultAvatar(InputStream stream) {
        repository.updateAvatarDate("default", stream);
    }

    /**
     * 根据账户名称返回账户头像名称
     */
    @Override
    public InputStream getAvatar(String accountId, int size) {
        return repository.getAvatarData(accountId);
    }

    /**
     * 获取账户的头像修
     *
     * @param account 账户
     * @return 一个账户的头像流
     */
    @Override
    public InputStream getAvatar(IAccount account, int size) {
        return getAvatar(account.getId(),size);
    }

    /**
     * 对明文密码加密
     */
    @Override
    public String encodePassword(IAccount account) throws AccountException {

        if (StringUtils.isEmpty(account.getPassword())) {
            throw AccountException.PasswordEx;
        }

        String pass = account.getAccount() + "{" + account.getPassword() + "}";


        //byte[] data = Charsets.UTF_8.encode(pass).array();

        return DigestUtils.md5Hex(pass);
    }

    /**
     * 对修改密码时的旧密码加密
     */
    private String encodePassword(IAccount account, String oldPassword) throws AccountException {
        if (StringUtils.isEmpty(oldPassword)) {
            throw AccountException.PasswordEx;
        }

        String pass = account.getAccount() + "{" + oldPassword + "}";

        //byte[] data = Charsets.UTF_8.encode(pass).array();

        return DigestUtils.md5Hex(pass);
    }

    /**
     * 更新账户密码
     */
    @Override
    public void changePassword(IAccount account, String oldPassword, String newPassword) throws AccountException {

        oldPassword = encodePassword(account, oldPassword);
        newPassword = encodePassword(account, newPassword);
        if (newPassword.length() < 6) throw AccountException.PasswordEx;
        if (!account.getPassword().equals(oldPassword)) throw AccountException.InconsistentPassword;
        if (repository.getAccountByAccount(account.getAccount()) == null) throw AccountException.UnAccount;

        repository.changePassword(account, newPassword);
    }

    /**
     * 更新账户密码
     */
    @Override
    public void changePassword(String account, String oldPassowd, String newPassword) throws AccountException {

        IAccount iAccount = repository.getAccountByAccount(account);
        if (newPassword.length() < 6) throw AccountException.PasswordEx;
        if (iAccount == null) throw AccountException.UnAccount;

        changePassword(iAccount, oldPassowd, newPassword);
    }

    /**
     * 更新账户信息
     */
    @Override
    public void updateAccount(IAccount baseAccount) throws AuthenticationException {

        BaseAccount acc = repository.getAccountByAccount(baseAccount.getAccount());

        if (StringUtils.isEmpty(baseAccount.getId())
                || acc == null) {
            throw AccountException.AccountEx;
        }

        if(StringUtils.isNotEmpty(baseAccount.getPassword()))
        {
            acc.setPassword(encodePassword(baseAccount));
        }

        //验证账户基本信息(未实现)
       // baseAccount.setPassword(encodePassword(baseAccount));//加密

        repository.updateAccount(acc);
    }

    /**
     * 根据帐号名称或者显示姓名获取分页信�
     */
    @Override
    public Page<IAccount> getPageData(AbstractPageRequest query) {
        Page<BaseAccount> page = repository.getPageData(query);

        List<IAccount> data = new ArrayList<IAccount>(page.getContent());

        return new PageImpl<>(data, query, page.getTotalElements());

    }

    @Override
    public IAccount getByAccount(String account) throws AccountException {
        return repository.getAccountByAccount(account);
    }

    @Override
    public ExpandAccount getAccountExpand(IAccount account) throws AccountException {
        return repository.getAccountExpand(account.getId());
    }

    @Override
    public IAccount getByToken(String token) {
        if (tokenMap.containsKey(token)) {
            String account = tokenMap.get(token);

            return accountManagerHashMap.get(account).getAccount();
        }

        return null;
    }

    /**
     * 删除一个账户
     *
     * @param id 帐号编码
     */
    @Override
    public void deleteAccountById(String id) throws AccountException {

        if (StringUtils.isEmpty(id)) throw AccountException.UnAccount;

        IAccount account = repository.getAccountByAccountId(id);

        deleteAccount(account);
    }

    /**
     * 删除账户信息
     *
     * @param account 账户信息
     */
    public void deleteAccount(IAccount account) throws AccountException {
        if (account != null) {
            //移除与之相关的角色

            //删除与之相关的权限
            if (repository.getAccountPermssion(account).size() > 0)
                repository.deleteAccountPermissionByAccountId(account.getId());

            //删除与之相关的扩展信息
                repository.deleteAccountExpandByAccount(account.getAccount());

            //上两项执行完毕再删除账户
            repository.deleteAccountById(account.getId());
        }
        else {
            throw AccountException.ExistAccount;
        }
    }

    @Override
    public Authentication doLogin(String account, String password, String ip) throws AuthenticationException {
        BaseAccount baseAccount = (BaseAccount)getByAccount(account);

        if (baseAccount == null)
            throw AccountException.UnAccount;
        if (baseAccount.isLocked())
            throw AccountException.LockedAccount;

        BaseAccount tempAccount = new BaseAccount(account);
        tempAccount.setPassword(password);

        //判断用户与用户名
        if (!StringUtils.equals(baseAccount.getPassword(), encodePassword(tempAccount))) {
            throw AccountException.PasswordEx;
        }


        Authentication auth = AuthenticationHolder.getAuthentication();

        try {
            if (auth == null) {
                auth = new Authentication(baseAccount);
            } else {
                //切换当前的账�
                auth.setPrincipal(baseAccount);
            }
        } catch (AccountException ex) {
            if (ex == AccountException.LoginAgain) {

                auth = AuthenticationHolder.getLoadedAuthentication(account);
            } else {
                throw ex;
            }
        }

        baseAccount.setLastLoginTime(new Date());
        baseAccount.setLoginCount(baseAccount.getLoginCount() + 1);

        repository.updateLoginInfo(baseAccount);

        return auth;
    }

    @Override
    public AccountManager doLogin(IUserSession userSession, String token) throws AuthenticationException {

        if (!tokenMap.containsKey(token)) {
            throw AccountException.UnAccount;
        }

        String account = tokenMap.get(token);

        String password = DigestUtils.md5Hex(userSession.getPlateformToken() + userSession.getAccount().getPassword());

        return doLogin(userSession, account, password);
    }

    @Override
    public AccountManager doLogin(IUserSession userSession, String account, String password) throws AuthenticationException {

        Assert.notNull(userSession);
        Assert.notNull(account);
        Assert.notNull(password);

        String token = userSession.getPlateformToken();

        BaseAccount baseAccount = (BaseAccount)getByAccount(account);

        if (baseAccount == null)
            throw AccountException.UnAccount;
        if (baseAccount.isLocked())
            throw AccountException.LockedAccount;


        String orgPassword = DigestUtils.md5Hex(token + baseAccount.getPassword());

        if (!StringUtils.equalsIgnoreCase(orgPassword, password)) {
            throw AccountException.PasswordEx;
        }

        AccountManager manager = null;

        //判断是否已经登录或者纯在用户管理对话
        if (accountManagerHashMap.containsKey(account)) {
            manager = accountManagerHashMap.get(account);
        }

        baseAccount.setLastLoginTime(new Date());
        baseAccount.setLoginCount(baseAccount.getLoginCount() + 1);

        //如果用户第一次登录
        if (manager == null) {
            baseAccount.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
            manager = new AccountManager(baseAccount);
            accountManagerHashMap.put(account, manager);
            tokenMap.put(baseAccount.getToken(), account);
        }

        repository.updateLoginInfo(baseAccount);

        manager.loadSession(userSession);


        if(log.isInfoEnabled()) {
            log.info("用户登录成功 用户名: " + manager.getAccount().getAccount() + " 名称: " + manager.getAccount().getShowName());
        }

        return manager;
    }

    @Override
    public void Logout(String account) {
        if (accountManagerHashMap.containsKey(account)) {
            AccountManager manager = accountManagerHashMap.get(account);
            String token = manager.getAccount().getToken();

            tokenMap.remove(token);
            manager.close();
            accountManagerHashMap.remove(account);
        }
    }

    @Override
    public BaseAccount getAccountByAccount(String account) throws AccountException {
        return repository.getAccountByAccount(account);
    }

    @Override
    public void resetPassword(String accountId) throws AccountException {
        BaseAccount account=repository.getAccountByAccountId(accountId);
        if (account == null) throw AccountException.UnAccount;
        account.setPassword("123456");


        account.setPassword(encodePassword(account));//加密

        repository.resetPassword(account);
    }

    @Override
    public List<BaseAccountRole> queryAccountByAccountId(String accountId) throws AccountException {
        return repository.queryAccountByAccountId(accountId);
    }

    @Override
    public List<BaseRole> queryAccountByRoleIds(List<BaseAccountRole> accountRoles) {
        return repository.queryAccountByRoleIds(accountRoles);
    }

    @Override
    public List<BaseAccount> getAccountByShowName(String showName) throws AccountException {
        return repository.getAccountByShowName(showName);
    }

    @Override
    public synchronized Date getCurrentAdate() {
        AccountManager manager = (AccountManager)getCurrentAccountManager();
        if(manager != null)
        {
            return manager.getConfig().getDefaultActionDate();
        }
        return new Date();
    }

    @Override
    public IAccount get(String pid) {
        return getByAccount(pid);
    }

    @Override
    public synchronized IAccountManager getCurrentAccountManager() {
        IAccountManager manager = threadLocal.get();

        if(manager == null) {
            IAccount account = createGuest();
            AccountManager temp = new AccountManager(account);
            setCurrentAccountManager(temp);
            manager = temp;
        }


        return manager;
    }

    @Override
    public synchronized void setCurrentAccountManager(AccountManager accountManager) {
        threadLocal.set(accountManager);
    }

    /**
     * 创建来宾用户..这个写法比例另类哦
     *
     * @return
     */
    @Override
    public IAccount createGuest() {

        if(log.isInfoEnabled())
        {
            log.info("创建一个游客账号.");
        }

        return new IAccount() {

            private String token = UUID.randomUUID().toString().replaceAll("-", "");
            private Date createTime = new Date();

            @Override
            public String getShowName() {
                return "Guest";
            }

            @Override
            public boolean isAdministrator() {
                return false;
            }

            @Override
            public String getId() {
                return "guest";
            }

            @Override
            public String getAccount() {
                return "guest";
            }

            @Override
            public String getToken() {
                return token;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String[] getRoles() {
                return new String[0];
            }

            @Override
            public Date getCreateTime() {
                return createTime;
            }

            @Override
            public Date getLastLoginTime() {
                return createTime;
            }

            @Override
            public boolean isLocked() {
                return false;
            }
        };
    }
}
