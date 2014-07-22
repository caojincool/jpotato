package com.lemsun.channel;

import org.springframework.context.ApplicationEvent;

/**
 * 消息对象事件. 当用户发消息请求的时候. 产生这个对象的实例. 并对外发布
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:47
 */
public class MessageEvent  extends ApplicationEvent {

	private IExcuteCommand excuteCommand;


	public MessageEvent(Object source, IExcuteCommand excuteCommand) {
		super(source);
		this.excuteCommand = excuteCommand;
	}


	public IExcuteCommand getExcuteCommand(){
		return excuteCommand;
	}
}
