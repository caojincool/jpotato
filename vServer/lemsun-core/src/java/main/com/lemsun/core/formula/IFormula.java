package com.lemsun.core.formula;

/**
 * 公式接口
 *
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午11:00
 */
public interface IFormula {

    /**
     * 获取公式执行的时间
     */
    String getAdate();


    /**
     * 获取引用的对象, 必须有至少一个引用
     */
    IFormulaRef[] getRefs();


    /**
     * 获取公式的列集合
     */
    IFColRange getColRange();

    /**
     * 获取公式的表达式
     */
    IFExpression getExpression();


    /**
     * 获取排序对象, 可以为 null. 不进行排序或者按默认情况排序
     */
    ISoft[] getSofts();


}
