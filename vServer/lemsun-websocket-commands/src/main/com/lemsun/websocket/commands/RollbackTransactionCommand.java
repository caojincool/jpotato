package com.lemsun.websocket.commands;

import com.lemsun.auth.AccountManager;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.service.IAccountManager;
import com.lemsun.websocket.AbstractSocketCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: 宗旭东
 * Date: 13-9-11
 * Time: 下午3:29
 */
public class RollbackTransactionCommand extends AbstractSocketCommand {

    @Autowired
    private IAccountService accountService;


    @Override
    public void excute() throws Exception {

        IAccountManager accountManager = accountService.getCurrentAccountManager();

        accountManager.getTransactionManager().start();

        getResponseSenter().sent(new SuccessCommand(getId()));
    }
}
