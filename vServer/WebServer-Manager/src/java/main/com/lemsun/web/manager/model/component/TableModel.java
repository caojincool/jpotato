package com.lemsun.web.manager.model.component;

import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.tables.TableColumn;
import com.lemsun.data.tables.TableFace;
import com.lemsun.data.tables.TableResource;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-15
 * Time: 上午9:13
 * To change this template use File | Settings | File Templates.
 */
public class TableModel {

    private String name;
    private String category;
    private String parentid;
    //表格显示名称
    private String dbtable;
    //表格代码
    private String code;
    //表格类型
    private int cate;
    //表格使用类型
    private int useBy;
    //是否开启
    private boolean enable;
    //开启时间
    private Date enableTime;
    //版本
    private int version;
//    //表格列
//    private List<TableColumn> columns;
//    //表格显示设置
//    private TableFace face;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDbtable() {
        return dbtable;
    }

    public void setDbtable(String dbtable) {
        this.dbtable = dbtable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(String cate) {
        if(!StringUtils.isEmpty(cate)) {
            try{
                this.cate = Integer.parseInt(cate);
            }catch (Exception ex){}
        }
    }

    public int getUseBy() {
        return useBy;
    }

    public void setUseBy(String useBy) {
        if(!StringUtils.isEmpty(useBy)) {
            try{
                this.useBy = Integer.parseInt(useBy);
            }catch (Exception ex){}
        }
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        try{
            this.enable=Boolean.parseBoolean(enable);
        }catch (Exception ex){}
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(String enableTime) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.enableTime =  sdf.parse(enableTime);
        }catch (Exception ex){}
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

    public TableResource getDataModel(DbConfigResource dbConfigResource) throws Exception {
        if(StringUtils.isEmpty(getName())||StringUtils.isEmpty(getCategory()))
            throw new Exception("名称和类别不能为空");
        if(dbConfigResource==null)
            throw new Exception("找不到数据库资源");
        TableResource tableResource=new TableResource(name,category,dbConfigResource);
        tableResource.setParentPid(getParentid());
        tableResource.setCate(getCate());
        tableResource.setCode(getCode());
        tableResource.setVersion(getVersion());
        tableResource.setDbtable(getDbtable());
        tableResource.setUseBy(getUseBy());
        tableResource.setEnable(isEnable());
        tableResource.setEnableTime(getEnableTime());
        return tableResource;
    }
}
