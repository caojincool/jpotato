package com.lemsun.client.core.formula;

/**
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午11:36
 */
public interface IFExpression {

    /**
     * 获取操作列
     */
    String getVar();

    /**
     * 获取操作引用对象, 如果引用定义了别名. 而且列的名称又不唯一. 就需要通过引用的别名进行区分
     */
    String getRef();

    /**
     * 获取操作值
     */
    Object getValue();

    /**
     * 获取值与列的操作符
     */
    FOperater getOperater();

    /**
     * 获取公式
     */
    IFormula getFormula();

    /**
     * 获取表达式与下个表达式的链接逻辑
     */
    FCondition getCondition();

    /**
     * 获取表达式的公式值
     */
    IFormula getFormulaValue();

    /**
     * 获取下一个的表达式
     */
    IFExpression getNextExpression();
}
