package com.lemsun.web.manager.model.component;

import com.lemsun.core.IResource;
import com.lemsun.core.ITreeNode;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.Navigation;
import com.lemsun.web.model.ExtTreeNode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-7
 * Time: 下午1:41
 * To change this template use File | Settings | File Templates.
 */
public class ResourceTreeStore {

    private boolean leaf;
    private List children;
    private String remark;
    private String pid;
    private String name;
    private String category;
    private String createUser;
    private Date updateTime;
    private boolean expanded=false;
    private List<ResourceTreeStore> listRts;

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



    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<ResourceTreeStore> getListRts() {
        return listRts;
    }

    public void setListRts(List<ResourceTreeStore> listRts) {
        this.listRts = listRts;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return DateFormat.getInstance().format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public List<ResourceTreeStore> convertLemsunResource(LemsunResource treeNode)
    {
        return  convertLemsunResource(treeNode, false);
    }
    public List<ResourceTreeStore>  convertLemsunResource(LemsunResource treeNode,boolean isExp)
    {

        listRts=new ArrayList<>();
        ResourceTreeStore resourceTreeStore=new ResourceTreeStore();
        if(isExp)
            resourceTreeStore.setExpanded(isExp);
        resourceTreeStore.loadData(treeNode);
        listRts.add(resourceTreeStore);
        return listRts;
    }

    public List<ResourceTreeStore>  convertLemsunResource(List<LemsunResource> treeNodes)
    {
        return convertLemsunResource(treeNodes,false);
    }

    public List<ResourceTreeStore>  convertLemsunResource(List<LemsunResource> treeNodes,boolean isExp)
    {
        listRts=new ArrayList<>();
        for(LemsunResource treeNode1:treeNodes){
            ResourceTreeStore resourceTreeStore=new ResourceTreeStore();
            if(isExp)
                resourceTreeStore.setExpanded(isExp);
            resourceTreeStore.loadData(treeNode1);
            listRts.add(resourceTreeStore);
        }
        return listRts;
    }

    protected void loadData(LemsunResource root) {

        setLeaf(root.getChild() == null || root.getChild().isEmpty()||root.getChild().size()==0);
        setPid(root.getPid());
        setName(root.getName());
        setCategory(root.getCategory());
        setCreateUser(root.getCreateUser());
        setUpdateTime(root.getUpdateTime());


        List<IResource> child=root.getChild();
        if(child != null&&child.size()>0) {
            List<Object> treeChild = new ArrayList<>();
            for(IResource iResource:child)
            {
                ResourceTreeStore resourceTreeStore=new ResourceTreeStore();
                resourceTreeStore.loadData((LemsunResource)iResource);
                treeChild.add(resourceTreeStore);
            }
            setChildren(treeChild);
        }

    }
}
