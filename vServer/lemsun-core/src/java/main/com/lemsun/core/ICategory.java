package com.lemsun.core;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * 定义类型
 * User: Xudong
 * Date: 12-10-17
 * Time: 下午2:33
 */
public interface ICategory extends ILoadChild<ICategory> {

    /**
     * 获取组件类别编码
     * @return 组件类别编码
     */
   public ObjectId getId();

    /**
     * 设置组件类别编码
     * @param id 组件类别编码
     */
    public void setId(ObjectId id);

    /**
     * 设置组件类型编码
     * @param pid 组件类型编码
     */
    public void setPid(String pid);

    /**
     * 获取组件类型编码
     * @return 组件类型编码
     */
    public String getPid();

    /**
     * 获取组件类型名称
     * @return 组件类型名称
     */
    public String getName();

    /**
     * 获取组件类型
     * @return 组件类型编码
     */
    public String getCategory();

    /**
     * 设置组件类型更新时间
     * @param updateTime 组件类型更新时间
     */
    public void setUpdateTime(Date updateTime);

    /**
     * 获取组件类型更新时间
     * @return 组件类型更新时间
     */
    public Date getUpdateTime();

	/**
	 *
	 * @return 返回类型的说明
	 */
	public String getRemark();

	/**
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark);


	/**
	 *
	 * @return 获取父节点
	 */
    public ICategory getParent();

    /**
     * 设置
     * @param category
     */
    public void setParent(ICategory category);


	/**
	 * 获取当前类型是否可以创建
	 * @return true表示可创建，false表示不可创建
	 */
	boolean isCreate();

    /**
     * 创建新类型必须指定是否为可创建的类型
     * @param create
     */
    void setCreate(boolean create);
}
