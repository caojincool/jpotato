package com.lemsun.channel;

/**
 * 可执行的命令对象. 这个接口用于执行用户请求过来的信息. 代表可执行的对象.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:45
 */
public interface IExcuteCommand {

	/**
	 * 开始执行
	 */
	void excute() throws ChannelException;

}
