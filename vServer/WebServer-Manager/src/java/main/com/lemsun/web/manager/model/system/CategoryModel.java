package com.lemsun.web.manager.model.system;

/**
 * 修改系统类型模型
 * User: gm
 * Date: 12-11-30
 * Time: 下午5:07
 */
public class CategoryModel{

	private String cid;
	private String cname;
	private String cpid;
	private String ccreateUser;
	private String cstartResource;
	private String cupdateTime;
	private String cstartscript;
	private String cendscript;
	private String ckey;
	private String cparam;

	/**
	 * 获取系统类型id
	 * @return 返回系统类型id
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * 设置系统类型id
	 * @param cid 系统类型id
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * 获取系统类型名称
	 * @return 返回系统类型名称
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * 设置系统类型名称
	 * @param cname 系统类型名称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * 获取系统类型pid
	 * @return 返回系统类型pid
	 */
	public String getCpid() {
		return cpid;
	}

	/**
	 * 设置系统类型pid
	 * @param cpid 系统类型pid
	 */
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	/**
	 * 获取系统类型创建人
	 * @return 返回系统类型创建人
	 */
	public String getCcreateUser() {
		return ccreateUser;
	}

	/**
	 * 设置系统类型创建人
	 * @param ccreateUser 系统类型创建人
	 */
	public void setCcreateUser(String ccreateUser) {
		this.ccreateUser = ccreateUser;
	}

	/**
	 * 获取系统类型开始资源
	 * @return 返回系统类型开始资源
	 */
	public String getCstartResource() {
		return cstartResource;
	}

	/**
	 * 设置系统类型开始资源
	 * @param cstartResource 系统类型开始资源
	 */
	public void setCstartResource(String cstartResource) {
		this.cstartResource = cstartResource;
	}

	/**
	 * 获取系统类型修改日期
	 * @return 返回系统类型修改日期
	 */
	public String getCupdateTime() {
		return cupdateTime;
	}

	/**
	 * 设置系统类型修改日期
	 * @param cupdateTime 系统类型修改日期
	 */
	public void setCupdateTime(String cupdateTime) {
		this.cupdateTime = cupdateTime;
	}

	/**
	 * 设置系统类型开始脚本
	 * @return 返回系统类型开始脚本
	 */
	public String getCstartscript() {
		return cstartscript;
	}

	/**
	 * 设置系统类型开始脚本
	 * @param cstartscript 系统类型开始脚本
	 */
	public void setCstartscript(String cstartscript) {
		this.cstartscript = cstartscript;
	}

	/**
	 * 获取系统类型结束脚本
	 * @return 返回系统类型结束脚本
	 */
	public String getCendscript() {
		return cendscript;
	}

	/**
	 * 设置系统类型结束脚本
	 * @param cendscript 系统类型结束脚本
	 */
	public void setCendscript(String cendscript) {
		this.cendscript = cendscript;
	}

	/**
	 * 获取系统类型Key
	 * @return 返回系统类型Key
	 */
	public String getCkey() {
		return ckey;
	}

	/**
	 * 设置系统类型Key
	 * @param ckey 系统类型Key
	 */
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}


	public String getCparam() {
		return cparam;
	}

	public void setCparam(String cparam) {
		this.cparam = cparam;
	}
}
