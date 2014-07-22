package com.lemsun.data.viproject.importmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-2-2
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class ImportIndexModel {
    /**
     * 存放4代中的完整code(主键)
     */
    private String code;

    /**
     * 父级code
     */
    private String parentCode;

    /**
     * 名字
     */
    private String name;

    /**
     * 子集
     */
    private List<ImportIndexModel> children;

    private List<ImportTableGroupModel> tableGroupModels;


    /**
     * 存放4代中的完整code(主键)
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 父级code
     */
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 名字
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 子集
     */
    public List<ImportIndexModel> getChildren() {
        return children;
    }

    public void setChildren(List<ImportIndexModel> children) {
        this.children = children;
    }

    public List<ImportTableGroupModel> getTableGroupModels() {
        return tableGroupModels;
    }


    /**
     * 添加表组
     * @param model
     */
    public void addTableGroupModel(ImportTableGroupModel model)
    {
        if(this.tableGroupModels==null)
            this.tableGroupModels=new ArrayList<>();
        this.tableGroupModels.add(model);
    }

    /**
     * 添加子集
     * @param indexModel
     */
    public void addIndexModelChild(ImportIndexModel indexModel)
    {
        if(this.children==null)this.children=new ArrayList<>();
        boolean havch=false;
        for (ImportIndexModel sun:this.children){
             if(sun.getCode().equals(indexModel.getCode()))
             {
                 havch=true;
                 break;
             }
        }
        if(!havch)
            this.children.add(indexModel);
    }
}
