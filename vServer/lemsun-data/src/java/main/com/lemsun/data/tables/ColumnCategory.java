package com.lemsun.data.tables;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 上午10:58
 */
public final class ColumnCategory {

    /**
     * 文本字段
     */
    public static final int TEXT = 1;

    /**
     * 整数
     */
    public static final int INT = 2;

    /**
     * 小数
     */
    public static final int DOUBLE = 3;
    /**
     * 数据字段
     */
    public static final int DATA = 4;
    /**
     * 时间字段
     */
    public static final int TIME = 5;
    /**
     * 多媒体字段
     */
    public static final int STREAM = 6;

    /**
     * 逻辑字段
     */
    public static final int BOOL = 7;

    /**
     * 表格主键
     */
    public static final int KEY = 8;

    /**
     * 更新时间标记
     */
    public static final int LastUpdate = 9;

    /**
     * 固定编码列
     */
    public static final int CODE = 10;

    /**
     * HTML 文本
     */
    public static final int HTML = 11;

    /**
     * 图片
     */
    public static final int Pic = 12;

    /**
     * 文件集
     */
    public static final int Files = 13;

    public static final int Xml = 14;



    /**
     * 将4代的类型名称. 转换成当前的的类型标识
     *
     * @param name 4代的类型名称
     */
    public static int parseCategory(String name) throws Exception {

        if (StringUtils.equalsIgnoreCase(name, "string")) {
            return TEXT;
        }


        if (StringUtils.equalsIgnoreCase(name, "Int32")) {
            return INT;
        }

        if (StringUtils.equalsIgnoreCase(name, "double")) {
            return DOUBLE;
        }

        if (StringUtils.equalsIgnoreCase(name, "DateTime")) {
            return TIME;
        }

        if (StringUtils.equalsIgnoreCase(name, "byte[]")) {
            return STREAM;
        }

        throw new Exception("当前列的类型不能识别: " + name);
    }

}
