package com.lemsun.client.core.mvc.lmsview;

/**
 * 视图输出
 * User: 宗旭东
 * Date: 13-12-10
 * Time: 下午6:15
 */
public interface IViewWriter {


    /**
     * 结束写入
     */
    void flush();

    /**
     * 增加数据
     * @param context
     */
    void append(Object context);

    /**
     * 将增加一行数据
     * @param context
     */
    void appendLine(Object context);


    void newLine();

    /**
     * 输出内容
     * @return
     */
    String toString();




}
