package com.lemsun.client.core.lmstable;

import org.apache.commons.lang3.StringUtils;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 上午10:58
 */
public final class ColumnCategory {


    /**
     * 外键字段
     */
    public static final int REF = -1;

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
    public static final int Update = 9;

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


    /**
     * XML 数据
     */
    public static final int Xml = 14;

    /**
     * 操作日期, 年 2002 月 200212 日 20020212
     */
    public static final int ADATE = 15;


    public static int getSQLType(int columnCategory)
    {
        int c = columnCategory;

        return c == ColumnCategory.KEY || c == ColumnCategory.ADATE || c == ColumnCategory.CODE ? Types.VARCHAR
                : c == ColumnCategory.TEXT || c == ColumnCategory.HTML ? Types.NVARCHAR
                : c == ColumnCategory.INT ? Types.INTEGER
                : c == ColumnCategory.DOUBLE ? Types.DOUBLE
                : c == ColumnCategory.BOOL ? Types.BIT
                : c == ColumnCategory.Files ? Types.NCLOB
                : c == ColumnCategory.TIME ? Types.DATE
                : c == ColumnCategory.DATA ? Types.VARBINARY
                : Types.NVARCHAR;
    }


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


    public static String getName(int category)
    {
        if(map.containsKey(category))
        {
            return map.get(category);
        }


        throw new RuntimeException("名字不存.");
    }

    public static final Map<Integer,String> map=new HashMap<>();

    static {
        map.put(-1,"外键");
        map.put(1,"文本字段");
        map.put(2,"整数");
        map.put(3,"小数");
        map.put(4,"数据");
        map.put(5,"时间");
        map.put(6,"多媒体");
        map.put(7,"逻辑");
        map.put(8,"表格主键");
        map.put(9,"更新时间标记");
        map.put(10,"固定编码");
        map.put(11,"HTML");
        map.put(12,"图片");
        map.put(13,"文件集");
        map.put(14,"XML");
        map.put(15,"操作日期");
    }
}
