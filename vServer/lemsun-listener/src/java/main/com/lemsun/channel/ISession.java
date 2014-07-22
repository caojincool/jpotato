package com.lemsun.channel;

import com.lemsun.data.connection.IDataSession;
import org.apache.mina.core.session.IoSession;

import java.nio.charset.CharsetEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 下午8:15
 */
public interface ISession extends IDataSession {



	/**
	 * 获取底层IO会话对象
	 * @return IoSession
	 */
	IoSession getIoSession();

	/**
	 * 获取通道对象
	 * @return 通道对象
	 */
	ZxdChannel getChannel();

	/**
	 *
	 * @return 返回用户的字符编码
	 */
	CharsetEncoder getCharsetEncoder();
}
