package com.lemsun.client.core.lmstable;


import java.util.HashSet;

/**
 * User: zongxudong
 * Date: 13-8-17
 * Time: 上午9:47
 * 表格的数据模型
 */

public class DataModel {

    private HashSet<TableExpression> expressions;


    private Table5Resource resource;

    /**
     * 获取模型对应的数据表组件
     * @return
     */
    public Table5Resource getResource() {
        return resource;
    }

    public void setResource(Table5Resource resource) {
        this.resource = resource;
    }

    /**
     * 获取表格设置的公式
     * @return
     */
    public HashSet<TableExpression> getExpressions() {
        return expressions;
    }

    /**
     * 设置表格的公式集合
     */
    public void setExpressions(HashSet<TableExpression> expressions) {
        this.expressions = expressions;
    }

}
