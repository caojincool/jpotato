package com.lemsun.client.core.formula;

/**
 * 公式执行片段
 *
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午11:33
 */
public interface IStatement {
    /**
     * 获取表达式左边的赋值公式
     */
    IFormula getFleft();

    /**
     * 获取表达式右边的公式
     */
    IFormula getFright();

    /**
     * 操作符合
     */
    FOperater getOperater();

}
