package com.lemsun.auth;

import com.lemsun.core.auth.AuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 账号管理的 Holder
 * User: Xudong
 * Date: 12-11-19
 * Time: 下午9:45
 */
@Deprecated
public class AuthenticationHolder {

    /**
     * 定义一个在 session 中绑定 权限管理对象的 Key
     */
    public static final String AuthenticationKey = Authentication.class.getName();

    private static ThreadLocal<Authentication> threadLocal = new ThreadLocal<>();
    private static HashMap<String, Authentication> authenticationHashMap = new HashMap<>(100);
    private static HashMap<String, Authentication> accountHashMap = new HashMap<>(100);
    private static Logger log = LoggerFactory.getLogger(AuthenticationHolder.class);

    /**
     * 获取一个当前权限管理对象
     */
	public static Authentication getAuthentication() {
        return threadLocal.get();
	}

    /**
     * 设置一个当前的权限管理对象
     */
    public static void setAuthentication(Authentication authentication) {
        threadLocal.set(authentication);
    }


    /**
     * 返回一个指定账号, 并且已经登录系统的权限管理对象
     * @param account  账号
     * @return 权限管理对象
     */
    public static Authentication getLoadedAuthentication(String account) throws AuthenticationException {

        if(StringUtils.isEmpty(account))
            throw AccountException.UnAccount;

        if(accountHashMap.containsKey(account))
            return accountHashMap.get(account);

        throw AccountException.UnAccount;
    }

    /**
     * 使用用户的唯一标示获取权限管理对象
     * @param token 唯一标示
     * @return 权限管理对象
     * @throws AuthenticationException 账号异常
     */
    public static Authentication getLoadedAuthenticationByToken(String token) throws AuthenticationException {
        if(StringUtils.isEmpty(token))
            throw AccountException.UnAccount;

        if(authenticationHashMap.containsKey(token))
            return authenticationHashMap.get(token);

        throw AccountException.UnAccount;
    }

    /**
     * 加载一个新的权限管理对象
     * @param authentication 权限管理对象
     */
    public static void loadAuthentication(Authentication authentication) {
        String uuid = authentication.getToken();

        if(authenticationHashMap.containsKey(uuid)) {
            authenticationHashMap.remove(uuid);
        }

        String account = authentication.getPrincipal().getAccount();
        if(accountHashMap.containsKey(account)) {
            accountHashMap.remove(account);
        }

        authenticationHashMap.put(uuid, authentication);
        accountHashMap.put(account, authentication);

        if(log.isDebugEnabled())
            log.debug("增加了一个账号登录 : {}, 当前登录人数为 : {}",
                    authentication.getPrincipal().getAccount(),
                    accountHashMap.size());
    }

    /**
     * 使用唯一标签, 清除一个权限管理对象.
     * @param token 唯一标示
     */
    public static void removeByToken(String token) {

        if(authenticationHashMap.containsKey(token))
        {
            Authentication auth = authenticationHashMap.get(token);
            String account = auth.getPrincipal().getAccount();
            if(accountHashMap.containsKey(account)) {
                accountHashMap.remove(account);

                if(log.isDebugEnabled())
                    log.debug("移除一个账号对象 {}, 当前的登录人数为　： {}", account, accountHashMap.size());

            }

            authenticationHashMap.remove(token);

        }
    }

    /**
     * 使用一个账号, 清除当前的权限管理.
     * <b>注意不能用一个匿名的账号来移除. 这个有可能是个</b>
     * @param account 账号
     */
    public static void removeByAccount(String account) {
        if(accountHashMap.containsKey(account)) {
            Authentication auth = accountHashMap.get(account);
            String token = auth.getToken();
            if(authenticationHashMap.containsKey(token)) {
                authenticationHashMap.remove(token);
            }

            accountHashMap.remove(account);

            if(log.isDebugEnabled())
                log.debug("移除一个账号对象 {}, 当前的登录人数为　： {}", account, accountHashMap.size());
        }
    }

}
