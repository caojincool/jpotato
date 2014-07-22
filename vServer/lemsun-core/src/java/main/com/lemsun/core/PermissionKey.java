package com.lemsun.core;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 权限节点对象. 权限节点按照树形结构组织. 一个父节点的权限分配可以覆盖子节点的默认权限
 * User: Xudong
 * Date: 12-11-23
 * Time: 上午10:40
 */
@Deprecated
public class PermissionKey extends AbstractLemsunResource implements ITreeNode<PermissionKey> {

	@DBRef
	private PermissionKey parent;

	private String remark;

	@Transient
	private List<PermissionKey> child;

	public PermissionKey(String name, String category) {
		super(name, category);
	}


	/**
	 *
	 * @return 返回权限的父节点
	 */
	public PermissionKey getParent() {
		return parent;
	}

	/**
	 *
	 * @param parent 权限的父节点
	 */
	public void setParent(PermissionKey parent) {
		this.parent = parent;
	}

	/**
	 *
	 * @return 备注信息
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 *
	 * @param remark 备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *
	 * @return 子节点
	 */
	public List<PermissionKey> getChild() {
		return child;
	}

	/**
	 *
	 * @param child 子节点
	 */
	public void setChild(List<PermissionKey> child) {
		this.child = child;
	}

	@Override
	public void loadChild(Collection collection) {
		Assert.notNull(collection);
	}
}
