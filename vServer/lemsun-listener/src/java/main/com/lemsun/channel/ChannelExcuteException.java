package com.lemsun.channel;

/**
 * 执行用户指令异常
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午3:00
 */
public class ChannelExcuteException extends ChannelException {


	public ChannelExcuteException(String msg) {
		this("5001", msg);
	}

	public ChannelExcuteException(String code, String msg) {
		super(code, msg);
	}
}
