package com.lemsun.channel;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:37
 */
public class ResponseHeader implements IResponseWriter {

	private String id;
	private ContextFormat dataFormat;
	private String actionCommand;
	private String userToken;
	private int contextLenght = -1;
	private String param;
	private MessageResponse response;
	private long runTime = 0;


	public ResponseHeader(MessageResponse response) {
		this.response = response;
	}

    /**
     * 获取回复主键
     */
	public String getId() {
		return id;
	}

    /**
     * 设置回复主键
     */
	void setId(String id) {
		this.id = id;
	}

    /**
     * 获取输出的格式数据
     */
	public ContextFormat getDataFormat() {
		return dataFormat;
	}

    /**
     * 设置输出的格式数据
     */
	public void setDataFormat(ContextFormat dataFormat) {
		this.dataFormat = dataFormat;
	}

    /**
     * 获取回复的命令
     */
	public String getActionCommand() {
		return actionCommand;
	}

    /**
     * 设置回复的命令
     */
	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

    /**
     * 获取用户的验证码
     */
	public String getUserToken() {
		return userToken;
	}

    /**
     * 设置用户的验证码
     */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

    /**
     * 获取内容长度
     */
	public int getContextLenght() {
		return contextLenght;
	}

    /**
     * 设置内容长度
     */
	public void setContextLenght(int contextLenght) {
		this.contextLenght = contextLenght;
	}

    /**
     * 获取回复的附加参数
     */
	public String getParam() {
		return param;
	}

    /**
     *
     * 设置回复的附加参数
     */
	public void setParam(String param) {
		this.param = param;
	}

    /**
     * 获取运行的时间, 单位(毫秒)
     */
	public long getRunTime() {
		return runTime;
	}

    /**
     * 设置运行的时间, 单位(毫秒)
     */
	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

    /**
     * 输出头部信息
     * @param buf 输出
     */
	@Override
	public void write(IoBuffer buf) {
	   StringBuilder header = new StringBuilder("begin ");

		header.append(getActionCommand())
			   .append(" /d ")
			   .append(getId())
			   .append(" /f ")
			   .append(getDataFormat().toString().toLowerCase())
			   .append(" /l ")
			   .append(getContextLenght())
			   .append(" /t ")
			   .append(getRunTime());

		if(!StringUtils.isEmpty(getUserToken()))
			   header.append(" /u ").append(getUserToken());

		if(!StringUtils.isEmpty(getParam())) {
			header.append(" /p ").append(getParam());
		}


		header.append("\n");

		//response.getSession().

		try {
			buf.putString(header.toString(), response.getSession().getCharsetEncoder());
		} catch (CharacterCodingException ignored) {

		}
	}
}
