package com.lemsun.web.manager.controller.model.component;

import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.viproject.FourTableGroup;
import com.lemsun.data.viproject.util.ReportType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public class FourTableGroupTreeStore {
    private String code;
    private String name;
    private String nameFormat;
    private String skinSolution;
    private String accountSymbol;
    private String booksGuid;
    private String reportGuid;
    private int category;
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



    public FourTableGroupTreeStore()
    {}
    public FourTableGroupTreeStore(ITableService tableService,DbConfigResource dbConfigResource)
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


    public List<FourTableGroupTreeStore>  convertViNavigate(List<FourTableGroup> fourTableGroups,ITableService tableService,DbConfigResource dbConfigResource)
    {
        this.tableService=tableService;
        this.dbConfigResource=dbConfigResource;
        List<FourTableGroupTreeStore> fourTableGroupTreeStores= new ArrayList<>();
        for(FourTableGroup fourTableGroup:fourTableGroups){
            FourTableGroupTreeStore fourTableGroupTreeStore=new FourTableGroupTreeStore(this.tableService,this.dbConfigResource);
            fourTableGroupTreeStore.loadData(fourTableGroup);
            fourTableGroupTreeStores.add(fourTableGroupTreeStore);
        }
        return fourTableGroupTreeStores;
    }


    protected void loadData(FourTableGroup root) {

        setCode(root.getCode());
        setName(root.getName());
        setCategory(root.getCategory());
        setNameFormat(root.getNameFormat());
        setAccountSymbol(root.getAccountSymbol());
        setSkinSolution(root.getSkinSolution());
        setBooksGuid(root.getBooksGuid());
        setReportGuid(root.getReportGuid());

        setCategoryChs(ReportType.getReportTypeName(root.getCategory()));
        if(root.getUpdateTime()!=null)
            setUpdate4Date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(root.getUpdateTime()));
        setAccountStateValue(root);

    }

    private void setAccountStateValue(FourTableGroup root)
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
                    setImport5Date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(tableGroupResource.getUpdateTime()));
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
