package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.service.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-11-12
 * Time: 下午3:03
 * 测试账户服务
 */
public class AccountServiceImplTest extends TestSupport {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testLogin(){
        IAccount account=accountService.login("admin","123123");

        getLog().info(account.getAccount());
    }
}
