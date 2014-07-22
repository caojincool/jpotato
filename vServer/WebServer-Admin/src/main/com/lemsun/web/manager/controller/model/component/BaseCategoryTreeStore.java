package com.lemsun.web.manager.controller.model.component;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;
import com.lemsun.web.model.ExtTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件类别树节点
 * User: Lucklim
 * Date: 12-12-4
 * Time: 下午3:03
 */
public class BaseCategoryTreeStore extends ExtTreeNode {

    private String category;
    private String createUser;
    private boolean expanded = false;
    private String iconCls;
    private List<BaseCategoryTreeStore> listBct;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<BaseCategoryTreeStore> getListBct() {
        return listBct;
    }

    public void setListBct(List<BaseCategoryTreeStore> listBct) {
        this.listBct = listBct;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<BaseCategoryTreeStore> convertBaseCategory(ICategory treeNode) {
        return convertBaseCategory(treeNode, false);
    }

    /**
     * 把组件类别转换成ExtTreeGrid模型
     * @param treeNode
     * @param isExp
     * @return
     */
    public List<BaseCategoryTreeStore> convertBaseCategory(ICategory treeNode, boolean isExp) {
        listBct = new ArrayList<>();
        BaseCategoryTreeStore baseCategoryTreeStore = new BaseCategoryTreeStore();
        if (isExp)
            baseCategoryTreeStore.setExpanded(isExp);
        baseCategoryTreeStore.loadTreeNode(treeNode);
        listBct.add(baseCategoryTreeStore);
        return listBct;
    }

    /**
     * 将List<Navigation>导航数据转为EXT的GridTree数据Model
     *
     * @param treeNodes
     * @return
     */
    public List<BaseCategoryTreeStore> convertBaseCategory(List<BaseCategory> treeNodes) {
        return convertBaseCategory(treeNodes, false);
    }


    public List<BaseCategoryTreeStore> convertBaseCategory(List<BaseCategory> treeNodes, boolean isExp) {
        listBct = new ArrayList<>();
        for (ICategory treeNode1 : treeNodes) {
            BaseCategoryTreeStore baseCategoryTreeStore = new BaseCategoryTreeStore();
            if (isExp)
                baseCategoryTreeStore.setExpanded(isExp);
            baseCategoryTreeStore.LoadData(treeNode1,this);
            listBct.add(baseCategoryTreeStore);
        }
        return listBct;
    }


    @Override
    protected void LoadData(ICategory root, ExtTreeNode tree) {
        if (root != null && tree != null) {
            ((BaseCategoryTreeStore) tree).setCategory(root.getCategory());
        }
        super.LoadData(root,tree);
    }

    @Override
    protected ExtTreeNode createNode() {
        return new BaseCategoryTreeStore();
    }
}
