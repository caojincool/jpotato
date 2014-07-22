package com.lemsun.web.manager.controller.model.packages;

import com.lemsun.auth.AccountManager;
import com.lemsun.component.lkg.BasePackage;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-26
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class PackageCreate {
    private String lid;
    private String name;
    private String createUser;
    private Date createTime;
    private String exportScript;
    private String remark;
    private String importScript;
    private String version;
    private String startFace;
    private Date updateTime;
    private String updateUser;
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExportScript() {
        return exportScript;
    }

    public void setExportScript(String exportScript) {
        this.exportScript = exportScript;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImportScript() {
        return importScript;
    }

    public void setImportScript(String importScript) {
        this.importScript = importScript;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStartFace() {
        return startFace;
    }

    public void setStartFace(String startFace) {
        this.startFace = startFace;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建一个基本的组件资源
     */
    public BasePackage encapseBasePackage() {
        BasePackage bp = new BasePackage();
        bp.setCreateUser(this.getCreateUser());
        bp.setExportScript(this.getExportScript());
        bp.setImportScript(this.getImportScript());
        bp.setCreateTime(this.getCreateTime());
        bp.setName(this.getName());
        bp.setRemark(this.getRemark());
        bp.setUpdateTime(this.getUpdateTime());
        bp.setUpdateUser(this.getCreateUser());
        bp.setStartFace(this.getStartFace());
        return bp;
    }
    /**
     * 创建一个基本的组件资源
     */
    public BasePackage encapseBasePackage(BasePackage bp) {
        bp.setExportScript(this.getExportScript());
        bp.setImportScript(this.getImportScript());
        bp.setStartFace(this.getStartFace());
        bp.setName(this.getName());
        bp.setRemark(this.getRemark());
        bp.setUpdateTime(this.getUpdateTime());
        bp.setUpdateUser(this.getCreateUser());
        return bp;
    }
}
