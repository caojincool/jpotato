package com.lemsun.core.member;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 定义一个用户的会话接口.
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午1:58
 */
public interface IUserSession {

	/**
	 *
	 * @return 会话主键
	 */
	String getId();

    /**
     * 获取一个真实会话对象的主键
     */
    String getSessionId();

	/**
	 *
	 * 启动时间
	 */
	Date getStartTime();


	/**
	 *
	 * @return 最后一次操作时间
	 */
	Date getLastAction();

    /**
     * 更新一次最后会话的时间为当前时间
     */
    void updateLastAction();

	/**
	 * 获取当前用户的对象
	 * @return 用户对象
	 */
	IAccount getAccount();


	/**
	 * 获取保存在当前用户中的对象
	 * @param key 对象键
	 * @return 保存对象
	 */
	Object getAttribute(String key);

	/**
	 * 将一个对象临时保存在用户会话中
	 * @param key 键
	 * @param value 值
	 */
	void setAttribute(String key, Object value);


	/**
	 * 获取用户设置的编码类型
	 */
	Charset getCharset();

    /**
     * 获取会话端的IP地址
     */
    String getClientIp();


    /**
     * 发送一个指令消息
     * @param command 命令
     */
    void SendMessage(IResponseCommand command);

    /**
     * 获取当前会话所属平台的 ID
     * @return 平台实例 ID
     */
    String getPlateformId();

    /**
     *
     * @return
     */
    String getPlateformToken();

    /**
     * 主动关闭用户的会话
     */
    void close();

}
