package com.lemsun.core;

/**
 * 树形结构
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午4:27
 */
public interface ITreeNode<T extends IResource> extends IResource, ILoadChild<T> {
	/**
	 *
	 * @return 返回父节点
	 */
	T getParent();

}
