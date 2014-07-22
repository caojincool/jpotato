package com.lemsun.formula.service;

import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.FormulaException;
import com.lemsun.core.formula.IFormula;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.TableData;

/**
 * 公式服务类
 * User: 宗
 * Date: 13-4-22
 * Time: 下午1:40
 */
public interface IFormulaService {


    /**
     * 使用公式获取公式的数据
     * @param formula
     * @return
     * @throws FormulaException
     */
    ArrayData getArrayData(IFormula formula);


    /**
     * 获取公式, 并使用查询条件
     *
     * @param formula
     * @param query
     * @return
     */
    ArrayData getArrayData(IFormula formula, FormulaQuery query);
    /**
     * 将JsonData数据设置到传入的公式中
     * @param formula 公式
     * @param jsonData 数据
     * @throws FormulaException 执行异常
     */
    void setFormulaData(IFormula formula, String dataType, String jsonData);

}
