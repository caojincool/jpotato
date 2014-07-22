package com.lemsun.web.manager.controller.model.permissionNode;

import com.lemsun.auth.ITreeNode;
import com.lemsun.auth.PermissionNode;
import com.lemsun.web.model.ExtTreeNode;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * 将权限节点转换exttree模型
 * User: dpyang
 * Date: 13-1-22
 * Time: 下午4:43
 */
public class PermissionNodeTreeStore extends ExtTreeNode {


    private String key;
    private boolean expanded=false;
    private String fullKey;
    private List<PermissionNodeTreeStore> listPermissionNode;

    /**
     * 获取全部节点Key
     * @return 全部节点Key
     */
    public String getFullKey() {
        return fullKey;
    }

    /**
     * 设置全部节点Key
     * @param fullKey 全部节点Key
     */
    public void setFullKey(String fullKey) {
        this.fullKey = fullKey;
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
    /**
     * 获取权限Key
     *
     * @return 权限Key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置权限Key
     *
     * @param key 权限Key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取子节点
     *
     * @return 子节点
     */
    public List<PermissionNodeTreeStore> getListPermissionNode() {
        return listPermissionNode;
    }

    /**
     * 设置子节点
     *
     * @param listPermissionNode 子节点
     */
    public void setListPermissionNode(List<PermissionNodeTreeStore> listPermissionNode) {
        this.listPermissionNode = listPermissionNode;
    }

    /**
     * 这里重载了加载节点对象,这里node 是权限节点是没有资源信息的节点
     * @param node 权限树形节点
     */
    public void loadTreeNode(com.lemsun.auth.PermissionNode node){
        loadData(node,this);
    }

    /**
     * 这里同样重构了节点树形加载对象,
     * @param root 这里是是权限树形节点
     * @param tree Ext树形节点
     */
    protected void loadData(PermissionNode root,PermissionNodeTreeStore tree){
        if(root==null)
            return;

        tree.setId(root.getId().toString());
        tree.setText(root.getName());
        tree.setKey(root.getKey());
        tree.setRemark(root.getRemark());
        tree.setFullKey(root.getFullKey());

        List child=root.getChild();
        tree.setLeaf(child == null || child.isEmpty());

        if(!tree.isLeaf() && child != null) {
            List<Object> treeChild = new ArrayList<>();

            for(Object c : child) {
                PermissionNodeTreeStore node = new PermissionNodeTreeStore();
                loadData((com.lemsun.auth.PermissionNode)c, node);
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
    public List<PermissionNodeTreeStore> convertPermissionNodeTreeStore(PermissionNode treeNode, boolean isExp) {
        listPermissionNode = new ArrayList<>();

        PermissionNodeTreeStore permissionNodeTreeStore = new PermissionNodeTreeStore();
        if (isExp)
            permissionNodeTreeStore.setExpanded(isExp);
        permissionNodeTreeStore.loadTreeNode(treeNode);
        listPermissionNode.add(permissionNodeTreeStore);

        return listPermissionNode;
    }

    /**
     * 把List权限节点转换成extjs识别树形
     *
     * @param treeNode 权限列表
     * @param isExp    是否展开
     * @return extjs 识别的树形节点
     */
    public List<PermissionNodeTreeStore> convertPermissionNodeTreeStore(List<PermissionNode> treeNode, boolean isExp) {
        listPermissionNode = new ArrayList<>();

        for (PermissionNode t : treeNode) {
            PermissionNodeTreeStore permissionNodeTreeStore = new PermissionNodeTreeStore();
            if (isExp)
                permissionNodeTreeStore.setExpanded(isExp);
            permissionNodeTreeStore.loadTreeNode(t);
            listPermissionNode.add(permissionNodeTreeStore);
        }
        return listPermissionNode;
    }

    /**
     * 把List权限节点转换成extjs识别树形
     *
     * @param treeNode 权限列表
     * @return extjs识别的树形默认不展开节点
     */
    public List<PermissionNodeTreeStore> convertPermissionNodeTreeStore(List<PermissionNode> treeNode) {
        return convertPermissionNodeTreeStore(treeNode, false);
    }
}
