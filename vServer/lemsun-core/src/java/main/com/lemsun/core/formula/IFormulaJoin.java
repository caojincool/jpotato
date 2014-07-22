package com.lemsun.core.formula;

/**
 * User: 宗旭东
 * Date: 14-1-21
 * Time: 下午3:13
 */
public interface IFormulaJoin {

    /**
     * 获取 Join 中的连接条件
     */
    FCondition getCondition();

    /**
     * 获取左边的引用, 可能为空, 如果为空. 默认就是当前的组件
     */
    String getLeftRef();


    /**
     * 获取左边的名称
     */
    String getLeftName();


    /**
     * 获取列之间的关联关系
     */
    FOperater getOperater();


    /**
     * 获取右边的引用, 不能为空
     */
    String getRightRef();

    /**
     * 获取右边的名称, 不能为空
     */
    String getRightName();

}
