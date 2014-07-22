package com.lemsun.core.formula;

/**
 * 公式的引用对象, 一个引用可以进行别名定义
 * User: 宗旭东
 * Date: 14-1-20
 * Time: 上午11:53
 */
public interface IFormulaRef {

    public final static int JOIN = 1;
    public final static int LEFTJOIN = 2;
    public final static int RIGHTJOIN = 3;
    public final static int FULLJOIN = 4;
    public final static int START = 0;

    /**
     * 获取公式的引用名称
     */
    public String getName();

    /**
     * 获取引用定义的别名, 为空就返回 getName() 引用的名称就是别名
     */
    public String getAlias();

    /**
     * 获取 JOIN 状态
     */
    public int getJoinState();


    /**
     * 获取组件连接条件
     */
    IFormulaJoin[] getJoins();

}
