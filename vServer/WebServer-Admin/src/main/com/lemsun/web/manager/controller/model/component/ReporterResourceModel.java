package com.lemsun.web.manager.controller.model.component;


import com.lemsun.reporter.ReporterParam;
import com.lemsun.reporter.ReporterResource;

import java.util.Set;

/**
 * 填报组件
 * Created by dpyang on 2014/5/22.
 */
public class ReporterResourceModel extends LemsunResourceModel {

    private String formula;
    private Set<ReporterParam> startParams;
    private String businessCode;
    //启动函数
    private String beforeScript;
    //修改函数
    private String updateScript;
    //勾稽关系
    private String articulationScript;
    //审核函数
    private String checkScript;
    //提交函数
    private String commitScript;
    //上报函数
    private String reportedScript;
    //关闭函数
    private String endScript;
    private int fileType;


    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getBeforeScript() {
        return beforeScript;
    }

    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Set<ReporterParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(Set<ReporterParam> startParams) {
        this.startParams = startParams;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getUpdateScript() {
        return updateScript;
    }

    public void setUpdateScript(String updateScript) {
        this.updateScript = updateScript;
    }

    public String getArticulationScript() {
        return articulationScript;
    }

    public void setArticulationScript(String articulationScript) {
        this.articulationScript = articulationScript;
    }

    public String getCheckScript() {
        return checkScript;
    }

    public void setCheckScript(String checkScript) {
        this.checkScript = checkScript;
    }

    public String getCommitScript() {
        return commitScript;
    }

    public void setCommitScript(String commitScript) {
        this.commitScript = commitScript;
    }

    public String getReportedScript() {
        return reportedScript;
    }

    public void setReportedScript(String reportedScript) {
        this.reportedScript = reportedScript;
    }

    public void convert(ReporterResource resource){
        resource.setPid(getPid());
        resource.setName(getName());
        resource.setBeforeScript(getBeforeScript());
        resource.setEndScript(getEndScript());
        resource.setFormula(getFormula());
        resource.setStartParams(getStartParams());
        resource.setRemark(getRemark());
        resource.setAllowRoles(getAllowRoles());
        resource.setBusinessCode(getBusinessCode());
        resource.setCategory(getCategory());
        resource.setCheckScript(getCheckScript());
        resource.setReportedScript(getReportedScript());
        resource.setUpdateScript(getUpdateScript());
        resource.setArticulationScript(getArticulationScript());
        resource.setPermissionScript(getPermissionScript());
        resource.setCommitScript(getCommitScript());
        resource.setFileType(getFileType());
    }
}
