package com.lemsun.core;

import java.util.Collection;
import java.util.List;

/**
 * 定义当前的类型可以加载和获取子节点
 * User: Xudong
 * Date: 12-11-23
 * Time: 下午11:14
 */
public interface ILoadChild<T> {

	/**
	 *
	 * @return 返回子节点
	 */
	List<T> getChild();


	/**
	 * 设置当前的子集合
	 * @param child 集合
	 */
	void setChild(List<T> child);


	/**
	 * 加载一个集合类型. 对类型进行判断加载
	 * @param collection 集合
	 */
	void loadChild(Collection collection);
}
