package com.lemsun.ldbc;

import org.apache.commons.lang3.StringUtils;

/**
 * 表格查询类.
 * User: 宗旭东
 * Date: 13-6-14
 * Time: 上午10:11
 */
public class TableQuery {

    private ITableResource tableResource;

    public static final String FirstCode = "first";
    public static final String LastCode = "last";
    public static final String NewCode = "new";


    private String adate;

    private Object code;
    private int dataCount = -1;
    private int start = -1;
    private int limit = -1;


    public TableQuery(ITableResource tableResource) {
        this.tableResource = tableResource;
    }


    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 返回操作日期字符串
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 设置操作日期字符串
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }

    /**
     * 获取查询编码, 当编码为字符型
     */
    public Object getCode() {
        return code;
    }

    /**
     * 设置查询编码
     */
    public void setCode(Object code) {
        this.code = code;
    }

    public void addWhere(ITableColumn column, Object value)
    {

    }

    public ITableResource getTableResource() {
        return tableResource;
    }

    protected void setTableResource(ITableResource tableResource) {
        this.tableResource = tableResource;
    }


    /**
     * 判断是否是单据号查询
     */
    public boolean isCodeSearch() {
        return getCode() != null;
    }


    /**
     * 判断是否是日期查询
     */
    public boolean isAdateSearch() {
        return  StringUtils.isNotEmpty(getAdate());
    }

    /**
     * 返回是否要返回数据的总数
     */
    public boolean isReturnDataCount()
    {
        return dataCount == 1;
    }

    /**
     * 获取当前是否是分页查询
     */
    public boolean isPageRequest() {
        return start != -1 && limit != -1;
    }

}
