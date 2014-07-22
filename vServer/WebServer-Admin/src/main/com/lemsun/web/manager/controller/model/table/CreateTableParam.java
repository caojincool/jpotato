package com.lemsun.web.manager.controller.model.table;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5GroupResource;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.lmstable.TableFace;
import com.lemsun.data.service.IDbService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 创建表格接受参数
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:23
 */
public class CreateTableParam {

    private String name;
    private String pid;
    private String code;
    private String codeFormat;
    private int cate;
    private String reamrk;
    private String dbPid;
    private Date dateTime = new Date();
    private boolean enable;
    private Date enableTime = new Date();
    private String timeFormat;
    private int version;
    private List<Column> columns;
    private TableFace face;
    private HashMap<String, String> formulas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getCodeFormat() {
        return codeFormat;
    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public String getReamrk() {
        return reamrk;
    }

    public void setReamrk(String reamrk) {
        this.reamrk = reamrk;
    }

    public String getDbPid() {
        return dbPid;
    }

    public void setDbPid(String dbPid) {
        this.dbPid = dbPid;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public TableFace getFace() {
        return face;
    }

    public void setFace(TableFace face) {
        this.face = face;
    }


    public HashMap<String, String> getFormulas() {
        return formulas;
    }

    public void setFormulas(HashMap<String, String> formulas) {
        this.formulas = formulas;
    }

    /**
     * 创建一个
     * @return
     */
    public Table5GroupResource createGroupResource(LemsunResource lemsunResource)
    {
        IDbService dbService = SpringContextUtil.getBean(IDbService.class);
        DbConfigResource dbResource = dbService.getDbconfigResource(getDbPid());

        Table5GroupResource table5GroupResource = new Table5GroupResource(lemsunResource, getCode(), dbResource);
        table5GroupResource.setTableCategory(getCate());
        table5GroupResource.setRemark(getReamrk());

        table5GroupResource.setCodeFormat(getCodeFormat());

        List<Table5Resource> tables = new ArrayList<>();

        tables.add(createTableResource());

        table5GroupResource.setTables(tables);

        return table5GroupResource;
    }


    public Table5Resource createTableResource() {
        Table5Resource table = new Table5Resource(getName(), null);

        table.setCate(getCate());
        table.setColumns(getColumns());
        table.setFace(getFace());
        table.setEnable(isEnable());
        table.setDateTime(getDateTime());
        table.setEnableTime(getEnableTime());
        table.setFormulas(getFormulas());
        table.setCodeFormat(getCodeFormat());

        return table;
    }
}
