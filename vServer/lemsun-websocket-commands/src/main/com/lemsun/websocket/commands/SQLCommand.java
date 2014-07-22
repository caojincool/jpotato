package com.lemsun.websocket.commands;

import com.lemsun.websocket.AbstractSocketCommand;
import com.lemsun.websocket.commands.models.SQLModel;

/**
 * User: 宗旭东
 * Date: 13-2-27
 * Time: 下午3:24
 */
public class SQLCommand extends AbstractSocketCommand {

    @Override
    public void excute() throws Exception {
        SQLModel model = getJsonParam(SQLModel.class);
        ReSqlCommand re = new ReSqlCommand(this);
        getResponseSenter().sent(re);
    }
}
