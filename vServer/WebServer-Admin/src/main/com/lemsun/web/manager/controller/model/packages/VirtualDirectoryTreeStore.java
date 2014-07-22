package com.lemsun.web.manager.controller.model.packages;

import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.core.ITreeNode;

import com.lemsun.web.model.ExtTreeNode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 将Navigation导航数据EXT模型.
 * User: Lucklim
 * Date: 12-12-3
 * Time: 下午1:41
 * To change this template use File | Settings | File Templates.
 */
public class VirtualDirectoryTreeStore extends ExtTreeNode{

	private String objid;

	private String pid;
	private String name;
	private String category;
	private String navigationcomponentid;
	private Date updateTime;
	private boolean expanded=false;
	private List<VirtualDirectoryTreeStore> listNts;

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNavigationcomponentid() {
		return navigationcomponentid;
	}

	public void setNavigationcomponentid(String navigationcomponentid) {
		this.navigationcomponentid = navigationcomponentid;
	}

	public String getUpdateTime() {
        if(updateTime!=null){
		return DateFormat.getInstance().format(updateTime);
        }else{
            return null;
        }

	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}


	/**
	 * 将Navigation导航数据转为EXT的GridTree数据Model
	 * @param treeNode
	 * @return
	 */
	public List<VirtualDirectoryTreeStore>  convertNavigation(ITreeNode treeNode)
	{
		return  convertNavigation(treeNode,false);
	}
	public List<VirtualDirectoryTreeStore>  convertNavigation(ITreeNode treeNode,boolean isExp)
	{

		listNts=new ArrayList<>();
		VirtualDirectoryTreeStore navigationTreeStore=new VirtualDirectoryTreeStore();
		if(isExp)
			navigationTreeStore.setExpanded(isExp);
		navigationTreeStore.loadTreeNode(treeNode);
		listNts.add(navigationTreeStore);
		return listNts;
	}


	/**
	 * 将List<Navigation>导航数据转为EXT的GridTree数据Model
	 * @param treeNodes
	 * @return
	 */
	public List<VirtualDirectoryTreeStore>  convertNavigation(List<VirtualDirectory> treeNodes)
	{
		return convertNavigation(treeNodes,false);
	}

	public List<VirtualDirectoryTreeStore> convertNavigation(List<VirtualDirectory> navigationList,boolean isExp)
	{
		listNts=new ArrayList<>();
		for(ITreeNode treeNode1:navigationList){
			VirtualDirectoryTreeStore navigationTreeStore=new VirtualDirectoryTreeStore();
			if(isExp)
				navigationTreeStore.setExpanded(isExp);
			navigationTreeStore.loadTreeNode(treeNode1);
			listNts.add(navigationTreeStore);
		}
		return listNts;
	}

	@Override
	protected void loadData(ITreeNode root, ExtTreeNode tree) {
		if(root != null && tree != null)
		{
			((VirtualDirectoryTreeStore)tree).setObjid(String.valueOf(((VirtualDirectory)root).getId()));
			((VirtualDirectoryTreeStore)tree).setPid(((VirtualDirectory)root).getPid());
			((VirtualDirectoryTreeStore)tree).setRemark(((VirtualDirectory)root).getRemark());

			((VirtualDirectoryTreeStore)tree).setCategory(((VirtualDirectory) root).getCategory());
			((VirtualDirectoryTreeStore)tree).setUpdateTime(((VirtualDirectory) root).getUpdateTime());
			((VirtualDirectoryTreeStore)tree).setName(((VirtualDirectory)root).getName());
		}
		super.loadData(root, tree);
	}

	@Override
	protected ExtTreeNode createNode() {
		return new VirtualDirectoryTreeStore();
	}
}
