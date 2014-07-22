package com.lemsun.core;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * 组件接口, 定义组件必须具备的属性 跟方法.
 * User: Xudong
 * Date: 12-10-16
 * Time: 下午2:20
 */
public interface IResource {

    /**
     * 获取数据库主键
     */
	ObjectId getId();

	/**
	 * 获取资源的主键
	 * @return 主键
	 */
	String getPid();

    /**
     * 业务主键
     */
	void setPid(String pid);

	/**
	 * 获取一个组件所属的父类组件. 组件与组件之间是一种树状结构的额类型.
	 */
	String getParentPid();


	/**
	 * 设置当前的父类组件
	 */
	void setParentPid(String parentPid);


	/**
	 *
	 * @return 获取资源的名称
	 */
	String getName();

    /**
     * 设置名称
     */
	void setName(String name);
	/**
	 *
	 * @return 返回类型
	 */
	String getCategory();

    /**
     * 设置类型的名称
     */
	void setCategory(String category);

	/**
	 *
	 * 获取创建人员
	 */
	String getCreateUser();


    /**
     * 设置创建的人员
     */
	void setCreateUser(String account);

	/**
	 * 获取人员权限
	 * @param account 账号
	 * @return 返回用户对当前资源的权限
	 */
	Permission getPermission(IAccount account);
    /**
     * 获取创建日期
     * @return 更新日期
     */
    Date getCreateTime();

    /**
     * 设置创建日期
     */
    void setCreateTime(Date createTime);

	/**
	 * 获取更新日期
	 * @return 更新日期
	 */
	Date getUpdateTime();

    /**
     * 设置更新账号
     */
	void setUpdateTime(Date lastUpate);

	/**
	 * 返回当前资源允许访问的角色
	 */
	String[] getAllowRoles();

    /**
     * 设置组件的权限列表
     * @param rpList 权限列表
     */
    void setRpList(List<ResourcePermission> rpList);

    /**
     * 获取组件的权限列表
     * @return 权限列表
     */
    List<ResourcePermission> getRpList();

    /**
     * 设置允许的角色
     */
	void setAllowRoles(String[] roles);

	/**
	 * 返回资源脚本
	 */
	String getPermissionScript();

    /**
     * 设置权限的脚本
     */
	void setPermissionScript(String script);

    /**
     * 获取当前的组件是否是系统组件
     */
    boolean isSystem();

    /**
     * 设置当前组件是否是系统组件
     */
    void setSystem(boolean system);

    /**
     * 获取发布状态 状态信息具体查看  ResourceState 类
     * @return
     */
    int getState();

    /**
     * 设置发布状态
     */
    void setState(int state);

    /**
     * 获取说明
     */
    String getRemark();

    /**
     * 设置说明
     */
    void setRemark(String remark);

    /**
     * 获取组件业务编码
     */
    String getBusinessCode();

    /**
     * 设置组件业务编码
     * @param businessCode
     */
    void setBusinessCode(String businessCode);
}
