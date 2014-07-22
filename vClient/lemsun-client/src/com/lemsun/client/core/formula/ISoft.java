/**
 * 5代网页端公式解析包
 */
package com.lemsun.client.core.formula;

/**
 * 定义一个排序
 *
 *
 * User: 宗旭东
 * Date: 14-1-20
 * Time: 上午11:50
 */
public interface ISoft {

    public static final int ASC = 1;
    public static final int DESC = 2;


    /**
     * 获取对什么列进行排序
     */
    public String getName();


    /**
     * 获取排序的引用表, 可以为空
     */
    public String getRef();

    /**
     * 获取排序的方式, 当前接口的常量 ASC, DESC
     */
    public int getSoft();

}
