package com.lemsun.auth;

import com.lemsun.core.IAccount;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.auth.AuthenticationException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import com.lemsun.auth.service.IAccountService;

import java.util.*;

/**
 * 权限管理对象.
 * User: Xudong
 * Date: 12-11-19
 * Time: 下午9:40
 */
public class Authentication {

	private IAccount principal;
    private String token;
    private static long tokenIndex = Long.MIN_VALUE;
    private HashSet<PermissionKey> permissionKeys = new HashSet<>();
    private Date actionDate = new Date();

    /**
     * 创建一个权限管理对象
     * @param account 账号信息
     */
    public Authentication(IAccount account) throws AuthenticationException {
        this(account, createToken());
    }

    /**
     * 构造一个权限管理对象
     * @param account 账号
     * @param token 唯一标识
     */
	public Authentication(IAccount account, String token) throws AuthenticationException {
		principal = account;
        this.token = token;
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        IAccountService accountService =(IAccountService)context.getBean(IAccountService.class);
        List<PermissionKey> permissionKeys1 = accountService.getAccountPermissions(account);
        List<BaseAccountRole>  accountRoles = accountService.queryAccountByAccountId(account.getAccount());
        if(accountRoles.size()>0){
            List<BaseRole>  baseRoles=accountService.queryAccountByRoleIds(accountRoles);
            for(BaseRole role :baseRoles){
               this.permissionKeys.addAll(role.getPermissions());
            }
        }
        this.permissionKeys.addAll(permissionKeys1);
        /*try {
            //不应该能够获取用户的账号
            Authentication authentication = AuthenticationHolder.getLoadedAuthentication(account.getAccount());


           throw AccountException.LoginAgain;
        } catch (AuthenticationException ignored) {
            //正常加载
            AuthenticationHolder.loadAuthentication(this);
        }*/
	}

    /**
     * 获取当前是否是一个游客登录身份
     * @return true 表示一个来宾账号
     */
    public boolean isGuest() {
        return StringUtils.equals(getPrincipal().getId(), "guest");
    }

    /**
     * 判断当前身份是否是一个系统内置的账号
     * @return true 表示是一个系统账号
     */
    public boolean isSystem() {
        return StringUtils.equals(getPrincipal().getId(), "system");
    }

    /**
     * 获取权限中的账号信息
     * @return 账号对象
     */
	public IAccount getPrincipal()
	{
		return principal;
	}

    /**
     * 设置当前的账号信息.
     * @param account 账号对象
     */
	public void setPrincipal(IAccount account)
	{
		this.principal = account;
	}

    /**
     * 获取一个账号管理的唯一标示
     */
    public String getToken() {
        return token;
    }

    /**
     * 销毁当前的权限管理对象
     */
    public void destroyed() {
        //AuthenticationHolder.removeByToken(getToken());
    }

    /**
     * 获取用户的操作时间
     * @return 操作时间
     */
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * 设置用户的操作时间
     * @param actionDate 操作时间
     */
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public HashSet<PermissionKey> getPermissionKeys() {
        return permissionKeys;
    }

    public void setPermissionKeys(HashSet<PermissionKey> permissionKeys) {
        this.permissionKeys = permissionKeys;
    }

    /**
     * 创建一个全局的唯一标识
     */
    public static String createToken() {
        tokenIndex++;
        String id = UUID.randomUUID().toString() + Long.toHexString(tokenIndex);
        byte[] data = Charsets.UTF_8.encode(id).array();
        return DigestUtils.md5Hex(data);
    }
}
