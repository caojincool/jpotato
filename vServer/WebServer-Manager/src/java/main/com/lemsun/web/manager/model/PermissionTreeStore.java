package com.lemsun.web.manager.model;

import com.lemsun.auth.Permission;
import com.lemsun.core.ITreeNode;
import com.lemsun.web.model.ExtTreeNode;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-18
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */

public class PermissionTreeStore extends ExtTreeNode {
    private String pid;
    private String name;
    private String category;
    private Date updateTime;
    private boolean expanded=false;
    private List<PermissionTreeStore> listNts;



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

    public String getUpdateTime() {
        return DateFormat.getInstance().format(updateTime);
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
     * 将Permission导航数据转为EXT的GridTree数据Model
     * @param treeNode
     * @return
     */
    public List<PermissionTreeStore>  convertPermission(ITreeNode treeNode)
    {
        return  convertPermission(treeNode,false);
    }
    public List<PermissionTreeStore>  convertPermission(ITreeNode treeNode,boolean isExp)
    {

        listNts=new ArrayList<>();
        PermissionTreeStore PermissionTreeStore=new PermissionTreeStore();
        if(isExp)
            PermissionTreeStore.setExpanded(isExp);
        PermissionTreeStore.loadTreeNode(treeNode);
        listNts.add(PermissionTreeStore);
        return listNts;
    }


    /**
     * 将List<Permission>导航数据转为EXT的GridTree数据Model
     * @param treeNodes
     * @return
     */
    public List<PermissionTreeStore>  convertPermission(List<ITreeNode> treeNodes)
    {
        return convertPermission(treeNodes,false);
    }

    public List<PermissionTreeStore>  convertPermission(List<ITreeNode> treeNodes,boolean isExp)
    {
        listNts=new ArrayList<>();
        for(ITreeNode treeNode1:treeNodes){
            PermissionTreeStore PermissionTreeStore=new PermissionTreeStore();
            if(isExp)
                PermissionTreeStore.setExpanded(isExp);
            PermissionTreeStore.loadTreeNode(treeNode1);
            listNts.add(PermissionTreeStore);
        }
        return listNts;
    }

    @Override
    protected void loadData(ITreeNode root, ExtTreeNode tree) {
        if(root != null && tree != null)
        {

        }
        super.loadData(root, tree);
    }

    @Override
    protected ExtTreeNode createNode() {
        return new PermissionTreeStore();
    }
}