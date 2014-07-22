package com.lemsun.channel;

/**
 * 消息请求接口. 每当用户发送了一个完整的请求头部信息. 这个对象就被创建.
 * User: Xudong
 * Date: 12-10-9
 * Time: 下午8:07
 */
public interface IMessageRequest  {

	/**
	 *
	 * @return 返回当前请求的用户 Session
	 */
	ISession getSession();

	/**
	 *
	 * @return 返回请求的头部信息
	 */
	RequestHeader getHeader();

	/**
	 * 返回用户的请求内容
	 * @return
	 */
	RequestContext getContext();
}
