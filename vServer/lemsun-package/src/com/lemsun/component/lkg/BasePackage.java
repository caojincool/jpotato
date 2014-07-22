package com.lemsun.component.lkg;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * User: dp
 * Date: 13-6-18
 * Time: 上午9:13
 */
@Document(collection = "ComponentPackage")
public class BasePackage {

    @Id
    private String id;
    private String lid;
    private String name;
    private String createUser;
    private Date createTime;

    private Date updateTime;
    private String updateUser;
    private String exportScript;
    private String remark;
    private String importScript;
    private String version;
    private String startFace;
    private List<BaseComponent> componentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**

     * 组件包编码
     */
    public String getLid() {
        return lid;
    }

    /**
     * 组件包编码
     */
    public void setLid(String lid) {
        this.lid = lid;
    }

    /**
     * 导出名称
     */
    public String getName() {
        return name;
    }

    /**
     * 组件包名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 创建者
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建者
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 导出脚本
     */
    public String getExportScript() {
        return exportScript;
    }

    /**
     * 导出脚本
     */
    public void setExportScript(String exportScript) {
        this.exportScript = exportScript;
    }


    /**
     * 组件包说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 组件包说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 导入脚本
     */
    public String getImportScript() {
        return importScript;
    }

    /**
     * 导入脚本
     */
    public void setImportScript(String importScript) {
        this.importScript = importScript;
    }

    /**
     * 导出版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 导出脚本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 开始页面
     *
     * @return
     */
    public String getStartFace() {
        return startFace;
    }

    /**
     * 开始页面
     *
     * @param startFace
     */
    public void setStartFace(String startFace) {
        this.startFace = startFace;
    }

    /**
     * 包组件
     */
    public List<BaseComponent> getComponentList() {
        return componentList;
    }

    /**
     * 包组件
     */
    public void setComponentList(List<BaseComponent> componentList) {
        this.componentList = componentList;
    }
}
