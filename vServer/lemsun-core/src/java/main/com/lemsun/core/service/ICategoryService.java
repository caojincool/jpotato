package com.lemsun.core.service;


import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;

import java.util.Collection;

/**
 * 组件类型对象操作服务.
 * 组件类型的操作仅限于对组件的编辑.不能增加.不能删除
 * User: Xudong
 * Date: 12-10-22
 * Time: 上午9:55
 */
public interface ICategoryService {

	/**
	 * 获取组件分类根节点
	 * @return 组件分类根节点
	 */
	ICategory getRoot();

	/**
	 * 根据组件分类名称返回组件类别对象
	 * @param category 组件代码
	 * @return 类型组件
	 */
	ICategory getByName(String category);

    /**
     * 根据pid获取BaseCategory
     */
    BaseCategory getByPid(String pid);
	/**
	 * 检查是否包含一个组件类型
	 */
	boolean contains(String category);

	/**
	 * 获取全部的组件类型
     * 包含根节点
	 */
	Collection<ICategory> getAll();

    /**
     * 返回可创建的类型集合
     * 不包含根节点的
     * @return 返回可创建的类型集合
     */
    Collection<ICategory> getBaseCategoryIsCreate();

    /**
     * 更新
     */
    void edit(BaseCategory category);
}
