package com.lemsun.web.manager.model.component;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.ITreeNode;
import com.lemsun.web.model.ExtTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-4
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class BaseCategoryTreeStore extends ExtTreeNode {

    private String pid;
    private String name;

    private String category;
    private String createUser;
    private boolean expanded=false;
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


    public List<BaseCategoryTreeStore>  convertBaseCategory(ITreeNode treeNode)
    {
        return  convertBaseCategory(treeNode,false);
    }
    public List<BaseCategoryTreeStore> convertBaseCategory(ITreeNode treeNode,boolean isExp)
    {

        listBct=new ArrayList<>();
        BaseCategoryTreeStore baseCategoryTreeStore=new BaseCategoryTreeStore();
        if(isExp)
            baseCategoryTreeStore.setExpanded(isExp);
        baseCategoryTreeStore.loadTreeNode(treeNode);
        listBct.add(baseCategoryTreeStore);
        return listBct;
    }
    /**
     * 将List<Navigation>导航数据转为EXT的GridTree数据Model
     * @param treeNodes
     * @return
     */
    public List<BaseCategoryTreeStore>  convertBaseCategory(List<BaseCategory> treeNodes)
    {
        return convertBaseCategory(treeNodes,false);
    }
    public List<BaseCategoryTreeStore>  convertBaseCategory(List<BaseCategory> treeNodes,boolean isExp)
    {
        listBct=new ArrayList<>();
        for(ITreeNode treeNode1:treeNodes){
            BaseCategoryTreeStore baseCategoryTreeStore=new BaseCategoryTreeStore();
            if(isExp)
                baseCategoryTreeStore.setExpanded(isExp);
            baseCategoryTreeStore.loadTreeNode(treeNode1);
            listBct.add(baseCategoryTreeStore);
        }
        return listBct;
    }



    @Override
    protected void loadData(ITreeNode root, ExtTreeNode tree) {
        if(root != null && tree != null)
        {
            ((BaseCategoryTreeStore)tree).setPid(((BaseCategory)root).getPid());
            ((BaseCategoryTreeStore)tree).setRemark(((BaseCategory)root).getRemark());
            ((BaseCategoryTreeStore)tree).setCategory(((BaseCategory) root).getCategory());
            ((BaseCategoryTreeStore)tree).setCreateUser(((BaseCategory) root).getCreateUser());
            ((BaseCategoryTreeStore)tree).setName(((BaseCategory)root).getName());
        }
        super.loadData(root, tree);
    }

    @Override
    protected ExtTreeNode createNode() {
        return new BaseCategoryTreeStore();
    }
}
