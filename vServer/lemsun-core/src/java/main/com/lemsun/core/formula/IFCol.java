package com.lemsun.core.formula;

/**
 * 公式中定义的一个显示字段
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 上午11:02
 */
public interface IFCol {

    /**
     * 获取列所带的函数名称, 如果没有返回 null
     */
    String getFun();

    /**
     * 获取公式的列名
     */
    String getCol();

    /**
     * 获取列的引用.  比如 : C01->A!A.B(A.Code=10) <br/>
     * 当用户定义了引用组件的时候. 可以对这个组件定义别名. 写在列的 "." 之前的就是引用名称
     */
    String getRef();


    /**
     * 获取列设置的别名  比如 : C01->A!A.B->C(A.Code=10) <br/>
     * 这里就将 A.B 设置成了 C 的别名
     */
    String getAlias();

}
