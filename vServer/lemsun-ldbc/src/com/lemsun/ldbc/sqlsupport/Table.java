package com.lemsun.ldbc.sqlsupport;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by xudong on 14-2-22.
 */
public class Table {

    private String name;
    private String aliay;

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliay() {
        if(StringUtils.isEmpty(aliay)) return name;
        return aliay;
    }

    public void setAliay(String aliay) {
        this.aliay = aliay;
    }


    public String toSQL()
    {
        return name + (StringUtils.isEmpty(aliay) ? "" : " as " + aliay);
    }
}
