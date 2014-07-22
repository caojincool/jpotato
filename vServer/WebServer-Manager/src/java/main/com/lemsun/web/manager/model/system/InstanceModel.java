package com.lemsun.web.manager.model.system;

import com.lemsun.core.Plateform;
import com.lemsun.core.Register;

import java.util.Date;

/**
 * 新增系统实例模型
 * User: gm
 * Date: 12-12-4
 * Time: 下午1:48
 */
public class InstanceModel {

	private String id;
	private String name;
	private String pid;


	private String categorytype;

	private String remark;
	private String address;
	private String qyDate;
	private String jsDate;

	private String ip;
	private String txKey;

	private String User;
	private String jiqicode;
	private String regRemark;

	private Register regInfo;

	/**
	 * 获取系统实例ID
	 * @return 系统实例ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置系统实例ID
	 * @param id 系统实例ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取系统实例名称
	 * @return 返回系统实例名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置系统实例名称
	 * @param name 系统实例名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取系统实例Pid
	 * @return 返回系统实例Pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置系统实例pid
	 * @param pid 系统实例pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 获取系统实例系统类型
	 * @return 系统实例系统类型
	 */
	public String getCategorytype() {
		return categorytype;
	}

	/**
	 * 设置系统实例系统类型
	 * @param categorytype 系统实例系统类型
	 */
	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}

	/**
	 * 获取系统实例备注信息
	 * @return 返回系统实例备注信息
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置系统实例备注信息
	 * @param remark 系统实例备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取系统实例地址信息
	 * @return 返回系统实例地址信息
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置系统实例地址信息
	 * @param address 系统实例地址信息
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取系统实例启用日期
	 * @return 返回系统实例启用日期
	 */
	public String getQyDate() {
		return qyDate;
	}

	/**
	 * 设置系统实例启用日期
	 * @param qyDate 系统实例启用日期
	 */
	public void setQyDate(String qyDate) {
		this.qyDate = qyDate;
	}

	/**
	 * 获取系统实例结束日期
	 * @return 返回系统实例结束日期
	 */
	public String getJsDate() {
		return jsDate;
	}

	/**
	 * 设置系统实例结束日期
	 * @param jsDate 系统实例结束日期
	 */
	public void setJsDate(String jsDate) {
		this.jsDate = jsDate;
	}

	/**
	 * 获取系统实例IP信息
	 * @return 返回系统实例IP信息
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置系统实例IP信息
	 * @param ip 系统实例IP信息
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取系统实例通讯密钥信息
	 * @return 返回系统实例通讯密钥信息
	 */
	public String getTxKey() {
		return txKey;
	}

	/**
	 * 设置系统实例通讯密钥信息
	 * @param txKey 系统实例通讯密钥信息
	 */
	public void setTxKey(String txKey) {
		this.txKey = txKey;
	}

	/**
	 * 获取系统实例使用人信息
	 * @return 返回系统实例使用人信息
	 */
	public String getUser() {
		return User;
	}

	/**
	 * 设置系统实例使用人信息
	 * @param user 系统实例使用人信息
	 */
	public void setUser(String user) {
		User = user;
	}

	/**
	 * 获取系统实例机器码信息
	 * @return 返回系统实例机器码信息
	 */
	public String getJiqicode() {
		return jiqicode;
	}

	/**
	 * 设置系统实例机器码信息
	 * @param jiqicode 系统实例机器码信息
	 */
	public void setJiqicode(String jiqicode) {
		this.jiqicode = jiqicode;
	}

	/**
	 * 获取系统实例注册相关信息
	 * @return 返回系统实例注册相关信息
	 */
	public String getRegRemark() {
		return regRemark;
	}

	/**
	 * 设置系统实例注册相关信息
	 * @param regRemark 系统实例注册相关信息
	 */
	public void setRegRemark(String regRemark) {
		this.regRemark = regRemark;
	}

	/**
	 * 获取系统实例注册相关信息
	 * @return 返回系统实例注册相关信息
	 */
	public Register getRegInfo() {
		return regInfo;
	}

	/**
	 * 设置系统实例注册相关信息
	 * @param regInfo 系统实例注册相关信息
	 */
	public void setRegInfo(Register regInfo) {
		this.regInfo = regInfo;
	}
}
