package com.lemsun.web.manager.controller.model.component;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.tables.TableColumn;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建五代表格参数
 * User: 宗旭东
 * Date: 13-3-19
 * Time: 上午11:37
 */
@Deprecated
public class CreateTable5Param {

    private String dbPid;
    private int tableType;
    private String name;
    private String code;
    private List<TableColumnParam> columns;

    /**
     * 获取数据库连接组件
     */
    public String getDbPid() {
        return dbPid;
    }

    public void setDbPid(String dbPid) {
        this.dbPid = dbPid;
    }

    /**
     * 获取表格类型
     */
    public int getTableType() {
        return tableType;
    }

    public void setTableType(int tableType) {
        this.tableType = tableType;
    }

    /**
     * 获取表格名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取代码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取列集合
     */
    public List<TableColumnParam> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumnParam> columns) {
        this.columns = columns;
    }

    /**
     * 将请求的参数信息创建出表格组对象
     */
    public TableGroupResource createTableGroup() {

        IDbService dbService = SpringContextUtil.getBean(IDbService.class);
        DbConfigResource dbResource = dbService.getDbconfigResource(getDbPid());

        TableGroupResource re = new TableGroupResource(getName(), getCode(), TableGroupResource.V, dbResource);
        List<TableResource> tables = new ArrayList<>(1);
        tables.add(createTableResource(re));
        re.setTables(tables);
        re.setTableCategory(getTableType());

        return re;
    }

    public TableResource createTableResource(TableGroupResource groupResource) {
        TableResource table = new TableResource(getName(), groupResource.getPid(), groupResource.getDbConfig());

        List<TableColumn> cols = new ArrayList<>(getColumns().size());

        for (TableColumnParam c : getColumns()) {
            TableColumn col = new TableColumn(c.getCode(), c.getType());
            col.setCol(c.getCode());
            col.setName(c.getName());
            col.setName1(c.getName2());
            col.setName2(c.getName3());
            col.setCategory(c.getType());
            col.setLength(c.getLen());
            col.setAlign(c.getAlign());
            col.setCheck(c.getCheck());
            //col.setDecimalDigits(c.getDigits());
            col.setFilter(c.getFilter());
            col.setFlag(c.getFlag());
            col.setReadOnly(c.isReadonly());
            col.setUnique(c.isUnique());
            col.setNull(c.isNull());
            col.setNegativeRed(c.getNegativeRed());
            col.setPrecision(c.getPrecision());
            col.setSectionLine(c.getSectionLine());
            col.setWidth(c.getWidth());
            col.setVisible(c.isVisible());
            col.setDefaultvalue(c.getDefaultvalue());
            cols.add(col);
        }

        table.setColumns(cols);

        return table;
    }

}
