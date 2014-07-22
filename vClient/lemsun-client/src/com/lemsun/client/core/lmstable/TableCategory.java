package com.lemsun.client.core.lmstable;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 上午10:46
 * 报表类型
 */
public final class TableCategory {
    /**
     * 未知表
     */
    public static final int UNKNOWN = 0;
    /**
     * 年表
     */
    public static final int YEAR = 1;
    /**
     * 一年365表
     */
    public static final int YEAR365 = 2;
    /**
     * 日记录表
     */
    public static final int DAYRECORD = 3;
    /**
     * 月表
     */
    public static final int MONTH = 4;
    /**
     * 旬表
     */
    public static final int NDAY = 5;
    /**
     * 日表
     */
    public static final int DAY = 6;
    /**
     * 单据表
     */
    public static final int FORM = 7;
    /**
     * 目录表
     */
    public static final int MENU = 8;
    /**
     * 参数表
     */
    public static final int PARAM = 9;
    /**
     * 账套
     */
    public static final int UNIT = 10;

    /**
     * 汇总表
     */
    public static final int SUM = 11;
    /**
     * 临时表
     */
    public static final int TEMP = 12;
    /**
     * 标识汇总表
     */
    public static final int MARKSUM = 13;
    /**
     * 单据目录表
     */
    public static final int FORMMENU = 14;

    /**
     * 五代标准表
     */
    public static final int VTABLE = 15;


    /**
     * 返回给出的表格对象是否是一张日期类型的表格. 日期类型是指表格内有日期信息的字段
     * @param resource
     * @return
     */
    public static boolean isAdateTable(Table5Resource resource) {

        int cate = resource.getCate();

        return isAdateTable(cate);

    }

    /**
     * 返回给出的表格对象是否是一张日期类型的表格. 日期类型是指表格内有日期信息的字段
     * @param cate
     * @return
     */
    public static boolean isAdateTable(int cate) {
        return cate == YEAR || cate == MONTH || cate == NDAY || cate == DAY || cate == YEAR365;
    }

    /**
     * 返回一个表格, 使用操作日期进行查询的数据的, 日期格式
     * @param cate
     * @param adate
     * @return
     */
    public static String getAdateSelect(int cate, Date adate)
    {
        if(cate == TableCategory.YEAR)
        {
            return DateFormatUtils.format(adate, "yyyyMM") + "01";
        }

        if(cate == TableCategory.MONTH)
        {
            return DateFormatUtils.format(adate, "yyyyMM") + "01";
        }

        if(cate == TableCategory.DAY)
        {
            return DateFormatUtils.format(adate, "yyyyMMdd");
        }

        return DateFormatUtils.format(adate, "yyyyMMdd");

    }


    public static String createCode(Table5Resource resource, Date adate) {

        int cate = resource.getCate();
        return createCode(cate, adate);
    }

    /**
     * 根据类型跟日期, 返回一个表格的Code值
     * @param cate
     * @param adate
     * @return
     */
    public static String createCode(int cate, Date adate) {
        String c;

        if(cate == TableCategory.YEAR)
        {
            c = DateFormatUtils.format(adate, "yyyy");
        }
        else if(cate == TableCategory.MONTH || cate == TableCategory.DAY)
        {
            c = DateFormatUtils.format(adate, "yyyyMM");
        }
        else if(cate == VTABLE) {
            c = "500";
        }
        else {
            c = "XX";
        }
        return c;
    }
}
