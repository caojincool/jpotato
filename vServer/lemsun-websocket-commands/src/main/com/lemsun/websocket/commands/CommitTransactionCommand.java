package com.lemsun.websocket.commands;

import com.lemsun.auth.AccountManager;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.service.IAccountManager;
import com.lemsun.websocket.AbstractSocketCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 执行提交数据事物
 * User: 宗旭东
 * Date: 13-9-11
 * Time: 下午2:00
 */
public class CommitTransactionCommand extends AbstractSocketCommand {

    @Autowired
    private IAccountService accountService;

    @Override
    public void excute() throws Exception {

        IAccountManager accountManager = accountService.getCurrentAccountManager();

        accountManager.getTransactionManager().commit();


        getResponseSenter().sent(new SuccessCommand(this.getId()));

    }
}
