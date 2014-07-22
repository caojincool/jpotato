package com.lemsun.web.model;

/**
 * 基本的模型请求对象
 * User: Xudong
 * Date: 12-11-2
 * Time: 下午4:37
 */
public class EntityRequest {
	private String code;
	private String account;
	private String sign;


	/**
	 *
	 * @return 返回请求系统代码
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 *
	 * @return 返回请求用户
	 */
	public String getAccount() {
		return account;
	}

	/**
	 *
	 * @param account 用户
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 *
	 * @return 返回签名值
	 */
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
