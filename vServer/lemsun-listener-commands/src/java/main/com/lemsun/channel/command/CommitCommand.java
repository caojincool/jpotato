package com.lemsun.channel.command;

import com.lemsun.channel.AbstractExcuteCommand;
import com.lemsun.channel.ChannelException;
import com.lemsun.data.connection.TransactionManager;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午2:57
 */
public class CommitCommand extends AbstractExcuteCommand {
	@Override
	public void excute() throws ChannelException {
		TransactionManager manager = getSession().getTransactionManager();

		try
		{
			manager.commit();
		}
		catch (SQLException ex) {


		}
	}
}
