package com.lemsun.web.manager.controller.model.component;


import java.util.Date;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-11-13
 * Time: 上午11:58
 */
public class Function {

    private String parentPid;
    private String name;
    private String nameCH;
    private String context;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private List<FunctionParam> funParams;


    public String getParentPid() {
        return parentPid;
    }

    public void setParentPid(String parentPid) {
        this.parentPid = parentPid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<FunctionParam> getFunParams() {
        return funParams;
    }

    public void setFunParams(List<FunctionParam> funParams) {
        this.funParams = funParams;
    }

}
