package com.lemsun.web.manager.model.component;

import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.tables.TableGroupResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-14
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class TableGroupModel {
    private String pid;
    private String name;
    private String category;
    private String parentid;
    private int tableCategory;
    //表格集合主键
    private String key;
    //编码
    private String code;
    //获取名称格式化的格式
    private String nameFormat;
    //返回版本号
    private int version;

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

    public int getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(int tableCategory) {
        this.tableCategory = tableCategory;
    }

    public String getCategory() {
        return category;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentId) {
        this.parentid = parentId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if(!StringUtils.isEmpty(version)) {
            try{
                this.version = Integer.parseInt(version);
            }catch (Exception ex){}
        }
    }

    public TableGroupResource getUpdateDateModel(IDbService dbService) throws  Exception{
        TableGroupResource tableGroupResource= dbService.getTableGroupResourceByPid(getPid());
        if(tableGroupResource==null)
            throw new Exception("找不到要修改的数据表组资源");
        tableGroupResource.setTableCategory(getTableCategory());
        tableGroupResource.setCode(getCode());
        tableGroupResource.setKey(getKey());
        tableGroupResource.setNameFormat(getNameFormat());
        return tableGroupResource;
    }

    public TableGroupResource getDataModel(DbConfigResource dbConfigResource) throws Exception {
        if(StringUtils.isEmpty(getName())||StringUtils.isEmpty(getCategory()))
            throw new Exception("名称和类别不能为空");
        if(dbConfigResource==null)
            throw new Exception("找不到数据库资源");
        TableGroupResource tableGroupResource=new TableGroupResource(name,category,dbConfigResource);
        tableGroupResource.setParentPid(getParentid());
        tableGroupResource.setTableCategory(getTableCategory());
        tableGroupResource.setCode(getCode());
        tableGroupResource.setKey(getKey());
        tableGroupResource.setNameFormat(getNameFormat());
        tableGroupResource.setVersion(getVersion());
        return tableGroupResource;
    }
}
