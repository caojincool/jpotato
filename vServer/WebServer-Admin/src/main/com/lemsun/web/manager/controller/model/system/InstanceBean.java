package com.lemsun.web.manager.controller.model.system;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.jackson.CustomJsonDateSerializer;
import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 系统实例模型
 * User: gm
 * Date: 12-12-11
 * Time: 上午9:53
 */
public class InstanceBean {

	private String id;
	private String name;
	private String ip;
	private String sysName;
	private String remark;
	private String address;
	private Date time;

	/**
	 * 获取系统实例pid
	 * @return 返回系统实例pid
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置系统实例pid
	 * @param id 系统实例pid
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
	 * 获取系统实例IP信息
	 * @return 返回系统实例IP信息
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置系统实例IP信息
	 * @param ip 返回系统实例IP信息
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取系统实例系统类型名称
	 * @return 返回系统实例系统类型名称
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * 设置系统实例系统类型名称
	 * @param sysName 系统实例系统类型名称
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * 获取系统实例说明信息
	 * @return 返回系统实例说明信息
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置系统实例说明信息
	 * @param remark 系统实例说明信息
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
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getTime() {
		return time;
	}

	/**
	 * 设置系统实例启用日期
	 * @param time 系统实例启用日期
	 */
	public void setTime(Date time) {
		this.time = time;
	}


}

