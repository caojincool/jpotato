package com.lemsun.web.manager.model;

import com.lemsun.core.ICategory;
import com.lemsun.core.ITreeNode;
import com.lemsun.web.model.ExtTreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-27
 * Time: 上午11:22
 */
public class CategoryTreeNode extends ExtTreeNode {
	private String category;



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	protected void loadData(ITreeNode root, ExtTreeNode tree) {
		if(root != null && tree != null)
		{
			((CategoryTreeNode)tree).setCategory(((ICategory)root).getCategory());
		}
		super.loadData(root, tree);
	}

	@Override
	protected ExtTreeNode createNode() {
		return new CategoryTreeNode();
	}
}
