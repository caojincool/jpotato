package com.lemsun.core.formula;

/**
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午11:06
 */
public interface IFColRange {

    /**
     * 获取集合中得全部列
     */
    IFCol[] getAll();

    /**
     * 确定是否是一个范围
     */
    boolean isRange();
}
