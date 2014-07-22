package com.lemsun.core.service;

/**
 * 编码服务. 对应加密程序中使用的加密处理
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:38
 */
public interface ICodecService {

	/**
	 * 加密字符串
	 * @param context 要加密的内容
	 * @return 编码字符
	 */
	String encode(String context);

	/**
	 * 解密字符串
	 * @param encode 编码字符
	 * @return 解密明文
	 */
	String decode(String encode);

}
