package com.lemsun.web.manager.model.component;

import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.viproject.ViNavigate;
import com.lemsun.data.viproject.util.ReportType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-4
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class ViNavigateTreeStore {
    private String code;
    private String name;
    private String nameFormat;
    private String skinSolution;
    private String accountSymbol;
    private String booksGuid;
    private String reportGuid;
    private int category;
    private boolean leaf;
    private boolean expanded=true;
    private List<ViNavigateTreeStore> children;
    //类型中文
    private String categoryChs;

    //状态 目录 有更新 未导
    private String accountState;

    //导入5代时间
    private String import5Date;
    //4代更新时间
    private String update4Date;


    ITableService tableService;

    DbConfigResource dbConfigResource;



    public ViNavigateTreeStore()
    {}
    public ViNavigateTreeStore(ITableService tableService,DbConfigResource dbConfigResource)
    {
        this.tableService=tableService;
        this.dbConfigResource=dbConfigResource;
    }


    public String getSkinSolution() {
        return skinSolution;
    }

    public void setSkinSolution(String skinSolution) {
        this.skinSolution = skinSolution;
    }

    public String getAccountSymbol() {
        return accountSymbol;
    }

    public void setAccountSymbol(String accountSymbol) {
        this.accountSymbol = accountSymbol;
    }

    public String getBooksGuid() {
        return booksGuid;
    }

    public void setBooksGuid(String booksGuid) {
        this.booksGuid = booksGuid;
    }

    public String getReportGuid() {
        return reportGuid;
    }

    public void setReportGuid(String reportGuid) {
        this.reportGuid = reportGuid;
    }

    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<ViNavigateTreeStore> getChildren() {
        return children;
    }

    public void setChildren(List<ViNavigateTreeStore> children) {
        this.children = children;
    }

    public String getCategoryChs() {

        return categoryChs;
    }

    public void setCategoryChs(String categoryChs) {
        this.categoryChs = categoryChs;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getImport5Date() {
        return import5Date;
    }

    public void setImport5Date(String import5Date) {
        this.import5Date = import5Date;
    }

    public String getUpdate4Date() {
        return update4Date;
    }

    public void setUpdate4Date(String update4Date) {
        this.update4Date = update4Date;
    }


    public List<ViNavigateTreeStore>  convertViNavigate(List<ViNavigate> navigates)
    {
        if(navigates==null)return new ArrayList<>();
         return convertViNavigate(navigates,null,null);
    }
    public List<ViNavigateTreeStore>  convertViNavigate(ViNavigate navigates,ITableService tableService,DbConfigResource dbConfigResource)
    {
        if(navigates==null)return new ArrayList<>();
        this.tableService=tableService;
        this.dbConfigResource=dbConfigResource;
        List<ViNavigateTreeStore> vnTrees=new ArrayList<>();
        ViNavigateTreeStore navigateTreeStore=new ViNavigateTreeStore(this.tableService,this.dbConfigResource);
        navigateTreeStore.loadData(navigates);
        vnTrees.add(navigateTreeStore);
        return vnTrees;
    }

    public List<ViNavigateTreeStore> convertViNavigate(List<ViNavigate> navigates,ITableService tableService,DbConfigResource dbConfigResource)
    {
        if(navigates==null)return new ArrayList<>();
        this.tableService=tableService;
        this.dbConfigResource=dbConfigResource;
        List<ViNavigateTreeStore> vnTrees=new ArrayList<>();
        for(ViNavigate treeNode1:navigates){
            ViNavigateTreeStore navigateTreeStore=new ViNavigateTreeStore(this.tableService,this.dbConfigResource);
            navigateTreeStore.loadData(treeNode1);
            vnTrees.add(navigateTreeStore);
        }
        return vnTrees;
    }

    protected void loadData(ViNavigate root) {

        setLeaf(root.getChild() == null || root.getChild().isEmpty() || root.getChild().size() == 0);
        setCode(root.getCode());
        setName(root.getName());
        setCategory(root.getCategory());
        setNameFormat(root.getNameFormat());
        setAccountSymbol(root.getAccountSymbol());
        setSkinSolution(root.getSkinSolution());
        setBooksGuid(root.getBooksGuid());
        setReportGuid(root.getReportGuid());

        if(this.tableService!=null){
            setCategoryChs(ReportType.getReportTypeName(root.getCategory()));
            if(root.getUpdateTime()!=null)
                setUpdate4Date(root.getUpdateTime().toString());
            setAccountStateValue(root);
        }

        List<ViNavigate> child=root.getChild();
        if(child != null&&child.size()>0) {
            List<ViNavigateTreeStore> treeChild = new ArrayList<>();
            for(ViNavigate iResource:child)
            {
                ViNavigateTreeStore navigateTreeStore=new ViNavigateTreeStore(this.tableService,this.dbConfigResource);
                navigateTreeStore.loadData(iResource);
                treeChild.add(navigateTreeStore);
            }
            setChildren(treeChild);
        }

    }

    private void setAccountStateValue(ViNavigate root)
    {
         if(root.getCategory()==ReportType.Index)
         {
             setAccountState("目录");
         }else{
             TableGroupResource tableGroupResource=  tableService.getFourByCode(this.dbConfigResource,root.getCode());
             if(tableGroupResource==null)
             {
                 setAccountState("未导");
             }else
             {
                 if(tableGroupResource.getUpdateTime()!=null)
                     setImport5Date(tableGroupResource.getUpdateTime().toString());
                 if(tableGroupResource.getUpdateTime().equals(root.getUpdateTime()))
                 {
                     setAccountState("无更新");
                 }else{
                     setAccountState("有更新");
                 }
             }
         }
    }
}
