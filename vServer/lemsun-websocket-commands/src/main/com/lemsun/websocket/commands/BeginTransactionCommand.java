package com.lemsun.websocket.commands;

import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.IAccountManager;
import com.lemsun.websocket.AbstractSocketCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接受用户启动事物请求
 * User: 宗旭东
 * Date: 13-9-11
 * Time: 上午10:52
 */
public class BeginTransactionCommand extends AbstractSocketCommand {

    @Autowired
    private IAccountCoreService accountService;


    @Override
    public void excute() throws Exception {

        IAccountManager accountManager = accountService.getCurrentAccountManager();

        accountManager.getTransactionManager().start();

        getResponseSenter().sent(new SuccessCommand(getId()));
    }
}
