package com.lemsun.channel.command;

import com.lemsun.channel.AbstractExcuteCommand;
import com.lemsun.channel.ChannelException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 创建SQL命令执行
 * User: Xudong
 * Date: 12-11-7
 * Time: 下午5:11
 */
public class SQLCommand extends AbstractExcuteCommand implements ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void excute() throws ChannelException {

	}
}
