package com.lemsun.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemsun.core.ICategory;
import com.lemsun.core.ITreeNode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午1:58
 */
public class ExtTreeNode {

	private String id;
	private String text;
	private boolean leaf;
	private List children;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * 将一个树形的资源对象加载的当前对象.
	 * @param node 节点
	 */
	public void loadTreeNode(ITreeNode node)
	{
		loadData(node, this);
	}

    /**
     * 将一个组件类别对象加载到当前对象
     * @param note 组件类别节点
     */
    public void loadTreeNode(ICategory note){
        LoadData(note,this);
    }

	protected ExtTreeNode createNode()
	{
		return new ExtTreeNode();
	}

    /**
     * 把组件分类加载到当前对象
     * @param root 组件分类对象
     * @param tree EXTtree
     */
    protected void LoadData(ICategory root,ExtTreeNode tree){
        if(root == null) return;

        tree.setId(root.getPid());
        tree.setText(root.getName());
        tree.setRemark(root.getRemark());

        List child = root.getChild();
        tree.setLeaf(child == null || child.isEmpty());

        if(!tree.isLeaf() && child != null) {
            List<Object> treeChild = new ArrayList<>();

            for(Object c : child) {
                ExtTreeNode node = createNode();
                LoadData((ICategory)c, node);
                treeChild.add(node);
            }

            tree.setChildren(treeChild);
        }
    }

	protected void loadData(ITreeNode root, ExtTreeNode tree) {
		if(root == null) return;

		tree.setId(root.getPid());
		tree.setText(root.getName());
		//tree.setRemark(root.getRemark());
		List child = root.getChild();
		tree.setLeaf(child == null || child.isEmpty());

		if(!tree.isLeaf() && child != null) {
			List<Object> treeChild = new ArrayList<>();

			for(Object c : child) {
				ExtTreeNode node = createNode();
				loadData((ITreeNode)c, node);
				treeChild.add(node);
			}

			tree.setChildren(treeChild);
		}
	}


	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(this);
		} catch (IOException e) {
			return "{}";
		}
	}
}
