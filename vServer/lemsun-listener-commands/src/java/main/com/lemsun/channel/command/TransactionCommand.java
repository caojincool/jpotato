package com.lemsun.channel.command;

import com.lemsun.channel.AbstractExcuteCommand;
import com.lemsun.channel.ChannelException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午2:50
 */
public class TransactionCommand extends AbstractExcuteCommand {



	@Override
	public void excute() throws ChannelException {
		getSession().getTransactionManager().startTransaction();
	}

}
