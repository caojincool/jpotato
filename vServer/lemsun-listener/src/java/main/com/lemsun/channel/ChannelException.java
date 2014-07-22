package com.lemsun.channel;

/**
 * 协议通道异常
 * User: Xudong
 * Date: 12-10-9
 * Time: 下午3:10
 */
public class ChannelException extends Exception {

	protected String code = "0001";

	public ChannelException(String msg) {
		this("0001", msg);
	}

	public ChannelException(String code, String msg)  {
		super(msg);
		this.code = code;
	}


	public String getCode() {
		return code;
	}


}


