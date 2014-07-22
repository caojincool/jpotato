package com.lemsun.channel.command;

import com.lemsun.channel.*;
import com.lemsun.data.connection.JdbcTemplate;
import com.lemsun.data.connection.TableSet;


import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-11
 * Time: 上午10:46
 */
public class QueryCommand extends AbstractExcuteCommand {

	private static int count;


	public QueryCommand() {
		count++;
	}

	public int getCount() {
		return count;
	}


	@Override
	public void excute() throws ChannelException {

		QueryModel q = getContext().getObject(QueryModel.class);

		try {

			JdbcTemplate template  = getSession().getTransactionManager().getTemplate();
			TableSet result = template.queryForTableSet(q.getDb(), q.getSql());

			getResponse().getHeader().setActionCommand("dataresult");
			getResponse().setResponse(result);

			//JdbcTemplate
		} catch (SQLException e) {
			throw new ChannelExcuteException("查询语句执行出错: " + e.getMessage());
		}

	}


}
