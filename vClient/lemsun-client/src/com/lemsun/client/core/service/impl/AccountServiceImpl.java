package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.model.BaseAccount;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.IRemoteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: 宗旭东
 * Date: 13-3-18
 * Time: 上午11:28
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private IRemoteService remoteService;
    private Host host;
    private String plateformtoken;
    private static final String AccountAddress="account/login";


    @Autowired
    public AccountServiceImpl(IRemoteService remoteService, @Qualifier("host") Host host){
        this.remoteService=remoteService;
        this.host=host;
        this.plateformtoken=host.getPlateform().getToken();
    }

    @Override
    public IAccount getCurrentAccount() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return  (IAccount)session.getAttribute(AccountAddress);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 账户
     */
    @Override
    public IAccount login(String username, String password) {

        String pwd= DigestUtils.md5Hex(plateformtoken+DigestUtils.md5Hex(username+"{"+password+"}"));

        String url = AccountAddress
                + String.format("?plateform=account&username=%s&password=%s",username, pwd);

        IResponseEntity<BaseAccount> entity  = remoteService.getAddressContext(
                    url,
                    new TypeReference<IResponseEntity<BaseAccount>>() {}
         );

        if(entity.isSuccess() && entity.getEntity() != null)
        {
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            IAccount account = entity.getEntity();
            session.setAttribute(AccountAddress, account);
            return account;
        }

        else {
            throw new LemsunException("登录异常");
        }
    }

    /**
     *
     */
    @Override
    public void logout() {
        HttpSession session = Host.getApplicationContext().getBean(HttpSession.class);
        session.invalidate();
    }
}
