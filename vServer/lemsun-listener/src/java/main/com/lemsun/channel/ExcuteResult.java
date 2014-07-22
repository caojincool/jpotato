package com.lemsun.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 代码执行的结果
 * User: Xudong
 * Date: 12-10-13
 * Time: 下午12:04
 */
public class ExcuteResult {

	private boolean success;

	private String msg;

	private String code;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public static ExcuteResult create(ChannelException ex) {
		ExcuteResult result = new ExcuteResult();
		result.setSuccess(false);
		result.setMsg(ex.getMessage());
		result.setCode(ex.getCode());
		return result;
	}


	public static ExcuteResult createSuccuess() {
		ExcuteResult result = new ExcuteResult();
		result.setSuccess(true);
		result.setCode("8888");
		return result;
	}

	public static ExcuteResult create(Exception ex) {
		ExcuteResult result = new ExcuteResult();
		result.setSuccess(false);
		result.setMsg(ex.getMessage());
		result.setCode("0000");
		return result;
	}

	public void write(IoBuffer buf) throws IOException {
		StringBuffer str = new StringBuffer("begin error /f json\n");
		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(this);

		str.append(json).append("\nend;\n");

		buf.putString(str.toString(), Charset.forName("utf-8").newEncoder());
	}
}
