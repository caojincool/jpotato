package com.lemsun.channel;

/**
 * 协议格式错误
 */
public class ChannelFormatException extends ChannelException {

	public ChannelFormatException(String msg) {
		super(msg);
		code = "1001";
	}

	public ChannelFormatException(String code, String msg) {
		super(code, msg);
	}
}
