package com.lemsun.core;

import java.util.Date;

/**
 * 注册对象
 * User: Xudong
 * Date: 12-11-22
 * Time: 下午3:58
 */
public class Register {
	private Date startTime;
	private Date endTime;
	private String code;
	private String regCode;


	public Register(String code, String regCode) {
		this.code = code;
		this.regCode = regCode;
	}

    /**
     * 注册开始日期
     */
	public Date getStartTime() {
		return startTime;
	}

    /**
     * 获取结束日期
     */
	public Date getEndTime() {
		return endTime;
	}

    /**
     * 获取代码
     */
	public String getCode() {
		return code;
	}

    /**
     * 获取注册代码
     */
	public String getRegCode() {
		return regCode;
	}
}
