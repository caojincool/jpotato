package com.lemsun.client.core.lmstable;

import com.lemsun.client.core.model.LemsunResource;

import java.util.List;

/**
 * User: 宗旭东
 * Date: 13-10-25
 * Time: 下午4:33
 */
public class Table5GroupResource extends LemsunResource {

    public static final String TYPE = "tablegroup";

    private List<Table5Resource> tables;

    private String code;
    private String key;
    private int tableCategory;
    private int lastIndex = 1;


    /**
     * 默认构造器
     */
    public Table5GroupResource()
    {

    }

    /**
     * 获取当前子表格
     */
    public List<Table5Resource> getTables() {
        return tables;
    }

    /**
     * 设置子表格
     */
    public void setTables(List<Table5Resource> tables) {
        this.tables = tables;
    }

    /**
     * 获取编码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取表格类型
     */
    public int getTableCategory() {
        return tableCategory;
    }

    /**
     * 设置表格类型
     */
    public void setTableCategory(int tableCategory) {
        this.tableCategory = tableCategory;
    }


    /**
     * 获取最后一次的索引
     */
    public int getLastIndex() {
        return lastIndex;
    }
}
