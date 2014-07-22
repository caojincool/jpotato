package com.lemsun.ldbc.service;

import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.IFormula;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.IDbType;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableData;

import java.util.Map;

/**
 *
 * 设计给表格公式执行
 *
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午10:48
 */
public interface IFormulaExcuter extends IDbType {

    /**
     * 将一个公式表达式
     * @param formula 更新表达式
     * @param resource 操作组件
     * @param dataType 数据类型
     * @param json json 数据
     */
    void update(ITableResource resource, IFormula formula, String dataType, String json);

    /**
     * 获取公式数据
     * @param refsMap 目标操作的组件
     * @param formula 公式对象
     * @return 数据模型
     */
    ArrayData getData(Map<String,ITableResource> refsMap, IFormula formula);


    /**
     * 获取公式, 并使用查询条件
     * @param refsMap
     * @param formula
     * @param query
     * @return
     */
    ArrayData getData(Map<String,ITableResource> refsMap, IFormula formula, FormulaQuery query);
}
