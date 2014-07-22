package com.lemsun.auth;

import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.service.IAccountManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 管理全局的 AccountManager 对象
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午8:08
 */
@Service
public class AccountManagerHolder implements ApplicationContextAware {

    private static ApplicationContext context;
    private static IAccountService accountService;


    public static IAccountManager getCurrentManager() {
        return accountService.getCurrentAccountManager();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        accountService = applicationContext.getBean(IAccountService.class);
    }
}
