package com.lemsun.auth;

import com.lemsun.auth.events.AccountEvent;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.AccountGlobalConfig;
import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.auth.AuthenticationException;
import com.lemsun.core.member.IUserSession;
import com.lemsun.core.service.IAccountManager;
import com.lemsun.core.service.ITransactionManager;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.TransactionManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;

/**
 * 对一个账号的管理, 比如说是扩展账号的权限, 会话, 已经连通情况. <br/>
 * 当构造一个账号对象的时候. 会初始化账号的权限. 已经跟踪账号的 Session 等等
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午2:25
 */
public class AccountManager implements IAccountManager {

    private IAccount account;
    private Authentication authentication;
    private HashSet<IUserSession> sessionMap = new HashSet<>();
    private static Logger log = LoggerFactory.getLogger(AccountManager.class);
    private AccountGlobalConfig config = new AccountGlobalConfig();
    private TransactionManager transactionManager;


    public AccountManager(IAccount account) throws AuthenticationException {
        this.account = account;
        authentication = new Authentication(account, Authentication.createToken());
        transactionManager = new TransactionManager(SpringContextUtil.getBean(DbManager.class), account);

    }

    /**
     * 加载当前用户登录的 Session
     * @param session 会话
     */
    public void loadSession(IUserSession session) {

        if(!sessionMap.contains(session)) {
            sessionMap.add(session);
        }
        ApplicationContext context = SpringContextUtil.getApplicationContext();

        AccountEvent event = new AccountEvent(context, AccountEvent.AccountEventType.Logon);

        context.publishEvent(event);

        if(log.isDebugEnabled())
            log.debug("在登录的用户上加载一个用户会话 SessionID : {} 平台 : {}, 当前用户会话数 : {}",
                    session.getSessionId(), session.getPlateformId(), sessionMap.size());
    }

    /**
     * 当一个账号结束了一个会话. 需要移除当前的会话
     * @param session 会话
     */
    public void removeSession(IUserSession session) {

        if(sessionMap.contains(session)) {
            sessionMap.remove(session);
        }

        if(log.isDebugEnabled())
            log.debug("移除一个用户会话 SessionID : {} 平台 : {}, 当前用户会话数 : {}",
                    session.getSessionId(), session.getPlateformId(), sessionMap.size());
    }

    /**
     * 使用会话主键移除用户的会话
     * @param sessionid
     */
    public void removeSession(String sessionid) {
        IUserSession temp = null;

        for(IUserSession s : sessionMap) {
            if(StringUtils.equals(s.getSessionId(), sessionid)) {
                temp = s;
                break;
            }
        }

        if(temp != null)
        removeSession(temp);
    }

    /**
     * 获取当前用户是否含有会话, 如果没有用户的会话应该将其移除
     * @return false 标示没有会话. 如果没有用户的会话应该将其移除
     */
    public boolean hasSession() {
        return sessionMap.size() > 0;
    }

    /**
     * 获取权限管理的账号
     */
    public IAccount getAccount() {
        return account;
    }

    /**
     * 获取权限信息
     * @return
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * 获取当前用户的事物管理器
     */
    public ITransactionManager getTransactionManager()
    {
        return transactionManager;
    }


    /**
     * 对一个正在使用的账号发送命令
     * @param command 命令
     */
    public void SentMessage(IResponseCommand command) {
        for(IUserSession session : sessionMap) {
            session.SendMessage(command);
        }
    }

    /**
     * 获取用户当前的配置对象
     * @return 配置对象
     */
    public AccountGlobalConfig getConfig() {
        return config;
    }

    public  void close() {
        if(sessionMap.iterator().hasNext()){
            sessionMap.iterator().next().close();
        }
      /*  for(IUserSession session : sessionMap) {
            session.close();
        }*/
    }


    private static IAccountService accountService = null;

    /**
     * 
     * @return
     */
    public static IAccountManager getCurrentManager() {

        if(accountService == null)
            accountService = SpringContextUtil.getBean(IAccountService.class);

        return accountService.getCurrentAccountManager();
    }
}
