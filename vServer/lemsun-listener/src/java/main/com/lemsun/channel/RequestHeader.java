package com.lemsun.channel;

import org.apache.commons.lang3.StringUtils;

/**
 * 请求头部信息对象封装.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:37
 */
public class RequestHeader {

	private String id;
	private ContextFormat dataFormat = ContextFormat.JSON;
	private String actionCommand;
	private String userToken;
	private int contextLenght = -1;
	private String param;
	private String command;

	public RequestHeader(String command) throws ChannelFormatException {
		this.command = command;
		parse();
	}

	/**
	 * 请求的唯一主键 从 /d, /id 参数中获取
	 * @return 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 请求的内容数据格式. 从 /f, /format 中获取
	 * @return 参数格式
	 */
	public ContextFormat getDataFormat() {
		return dataFormat;
	}

	/**
	 * 请求的命令. 从 /c, /command 中获取
	 * @return 执行的命令
	 */
	public String getActionCommand() {
		return actionCommand;
	}

	/**
	 * 用户识别码. 从 /u, /user 中获取
	 * @return 用户识别码
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * 固定的内容长度. 从 /l, /lenght 中获取, 如果长度为-1. 那么就是由用户发送的end; 标记作为结束. 长度不限
	 * @return 内容长度
	 */
	public int getContextLenght() {
		return contextLenght;
	}

	/**
	 * 扩展参数, 从 /p , /param 中获取
	 * @return 参数
	 */
	public String getParam() {
		return param;
	}

	/**
	 * 解析
	 */
	private void parse() throws ChannelFormatException {


		if(StringUtils.isEmpty(command) || command.length() < 2)
			throw new ChannelFormatException("头部格式不正确, 不能为空. 或者过小的长度");

		char[] chars = command.toCharArray();

		StringBuilder builder = new StringBuilder();
		boolean isH = false; //标记是否开始解析头部
		boolean isC = false; //标记是否开始解析内容
		String lastH = "";
		String lastC = "";
		char c = ' ';
		for(int i=0; i<chars.length; i++) {
			c = chars[i];

			if(c == '/' || c == '\n') {


				if(StringUtils.isEmpty(actionCommand))
					actionCommand = getAndClear(builder);

				isH = true;

				if(isC) {
					lastC = getAndClear(builder);
					isC = false;
					setValue(lastH, lastC);
				}

			}
			else if(c == ' ') {

				if(isH) {
					lastH = getAndClear(builder);
					isH = false;
					isC = true;
				}


			}
			else {
				builder.append(c);
			}

		}


		if(StringUtils.isEmpty(actionCommand))
			throw new ChannelFormatException("头部信息缺少命令内容. 请检查 /c, /command 参数是否传递");
	}


	private static String getAndClear(StringBuilder builder) {
		String c = builder.toString().trim();
		builder.setLength(0);
		return c;
	}

	private void setValue(String h, String c) throws ChannelFormatException {
		if(h == null) return;

		if("d".equals(h) || "id".equals(h)) {
			id = c;
		}
		else if("f".equals(h) || "format".equals(h)) {
			//dataFormat = c;

			dataFormat = ContextFormat.valueOf(c.toUpperCase());
		}
		else if("c".equals(h) || "command".equals(h)) {
			actionCommand = c;
		}
		else if("u".equals(h) || "user".equals(h)) {
			userToken = c;
		}
		else if("l".equals(h) || "length".equals(h)) {
			contextLenght = Integer.parseInt(c);
			if(contextLenght < 0) {
				throw new ChannelFormatException("内容长度不能小于 0");
			}
		}
		else if("p".equals(h) || "param".equals(h)) {
			param = c;
		}
	}


    /**
     * 获取当前的请求命令是否是退出命令
     */
    public boolean isQuit() {
        return StringUtils.equals(actionCommand, "quit");
    }


	@Override
	public String toString() {
		return "RequestHeader{" +
				"command='" + command + '\'' +
				'}';
	}
}
