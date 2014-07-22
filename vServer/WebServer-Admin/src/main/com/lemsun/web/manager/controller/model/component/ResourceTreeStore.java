package com.lemsun.web.manager.controller.model.component;


import com.lemsun.core.LemsunResource;
import com.lemsun.web.model.ExtTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-10-30
 * Time: 下午1:09
 */
public class ResourceTreeStore  extends ExtTreeNode {

    private String pid;
    private boolean expanded=false;
    private String category;
    private String parentPid;
    private List<ResourceTreeStore> listLemsunResource;

    public String getParentPid() {
        return parentPid;
    }

    public void setParentPid(String parentPid) {
        this.parentPid = parentPid;
    }

    /**
     * 设置是否存在子节点
     *
     * @param expanded 是否存在子节点
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * 是否展开节点
     * @return
     */
    public boolean isExpanded() {
        return expanded;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取子节点
     *
     * @return 子节点
     */
    public List<ResourceTreeStore> getListLemsunResource() {
        return listLemsunResource;
    }

    /**
     * 设置子节点
     *
     * @param listLemsunResource 子节点
     */
    public void setListLemsunResource(List<ResourceTreeStore> listLemsunResource) {
        this.listLemsunResource = listLemsunResource;
    }

    /**
     * 这里重载了加载节点对象,这里node 是权限节点是没有资源信息的节点
     * @param node 权限树形节点
     */
    public void loadTreeNode(LemsunResource node){
        loadData(node,this);
    }

    /**
     * 这里同样重构了节点树形加载对象,
     * @param root 这里是是权限树形节点
     * @param tree Ext树形节点
     */
    protected void loadData(LemsunResource root,ResourceTreeStore tree){
        if(root==null)
            return;

        tree.setId(root.getId().toString());
        tree.setText(root.getName());
        tree.setPid(root.getPid());
        tree.setCategory(root.getCategory());
        tree.setParentPid(root.getParentPid());
        List child=root.getChild();
        tree.setLeaf(child == null || child.isEmpty());

        if(!tree.isLeaf() && child != null) {
            List<Object> treeChild = new ArrayList<>();

            for(Object c : child) {
                ResourceTreeStore node = new ResourceTreeStore();
                loadData((LemsunResource)c, node);
                treeChild.add(node);
            }

            tree.setChildren(treeChild);
        }
    }


    /**
     * 把业务树形结构转换成Extjs识别的树形
     *
     * @param treeNode 权限树接口
     * @param isExp    是否展开
     * @return ext树节点
     */
    public List<ResourceTreeStore> convertResourceTreeStore(LemsunResource treeNode, boolean isExp) {
        listLemsunResource = new ArrayList<>();

        ResourceTreeStore ResourceTreeStore = new ResourceTreeStore();
        if (isExp)
            ResourceTreeStore.setExpanded(isExp);
        ResourceTreeStore.loadTreeNode(treeNode);
        listLemsunResource.add(ResourceTreeStore);

        return listLemsunResource;
    }

    /**
     * 把List权限节点转换成extjs识别树形
     *
     * @param treeNode 权限列表
     * @param isExp    是否展开
     * @return extjs 识别的树形节点
     */
    public List<ResourceTreeStore> convertResourceTreeStore(List<LemsunResource> treeNode, boolean isExp) {
        listLemsunResource = new ArrayList<>();

        for (LemsunResource t : treeNode) {
            ResourceTreeStore ResourceTreeStore = new ResourceTreeStore();
            if (isExp)
                ResourceTreeStore.setExpanded(isExp);
            ResourceTreeStore.loadTreeNode(t);
            listLemsunResource.add(ResourceTreeStore);
        }
        return listLemsunResource;
    }

    /**
     * 把List权限节点转换成extjs识别树形
     *
     * @param treeNode 权限列表
     * @return extjs识别的树形默认不展开节点
     */
    public List<ResourceTreeStore> convertResourceTreeStore(List<LemsunResource> treeNode) {
        return convertResourceTreeStore(treeNode, false);
    }
}
