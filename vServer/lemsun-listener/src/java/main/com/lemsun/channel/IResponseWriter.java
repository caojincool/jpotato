package com.lemsun.channel;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 定义一个对象. 表示这个对象可以直接写回用户的
 * User: Xudong
 * Date: 12-10-9
 * Time: 下午8:40
 */
public interface IResponseWriter {

	/**
	 *
	 * @param buffer 输出字符流
	 */
	void write(IoBuffer buffer);

}
