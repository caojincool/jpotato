package com.lemsun.ldbc.sqlsupport;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by xudong on 14-2-22.
 */
public class Column {

    private String name;
    private String aliay;
    private String fun;
    private Table table;


    public Column(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliay() {
        return aliay;
    }

    public void setAliay(String aliay) {
        this.aliay = aliay;
    }

    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    public String ToSQL()
    {
        return (getTable() != null ? getTable().getAliay() + "." : "") +  name  + (StringUtils.isEmpty(getAliay()) ? "" : " as " + getAliay());
    }

}
