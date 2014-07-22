package com.lemsun.channel;

/**
 * 定义用户执行命令对象创建的方法
 * User: Xudong
 * Date: 12-10-10
 * Time: 上午10:55
 */
public interface ICommandFactory {

	/**
	 * 创建一个用于执行用户请求的对象
	 * @param request 请求对象
	 * @return 执行命令
	 */
	IExcuteCommand createCommand(MessageRequest request);

}
