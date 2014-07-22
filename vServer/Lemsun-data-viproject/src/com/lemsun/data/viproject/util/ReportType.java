package com.lemsun.data.viproject.util;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-28
 * Time: 上午9:44
 * 4代中总报表目录的报表类型
 */
public class ReportType {
    public static final int None = 0;

    /**
     * 年
     */
    public static final int Year  = 1;

    /**
     *  一年365
     */
    public static final int AllInOneYear=2;

    /**
     * 日记
     */
    public static final int EveryDay=3;

    /**
     * 月
     */
    public static final int Month=4;

    /**
     * 旬
     */
    public static final int TenDay=5;

    /**
     * 日
     */
    public static final int Day=6;

    /**
     * 单据
     */
    public static final int Bill=7;

    /**
     * 目录
     */
    public static final int Index=8;

    /**
     * 参数表
     */
    public static final int Parameter=9;

    /**
     * 帐套
     */
    public static final int Account=10;

    /**
     * 汇总明细查询表
     */
    public static final int TotalToDetail=11;

    /**
     *临时查询表
     */
    public static final int Query=12;

    /**
     * 标志汇总表
     */
    public static final int TotalOfSign=13;

    /**
     *单据目录
     */
    public static final int IndexOfBill=14;

    /**
     * 系统
     */
    public static final int System =-1;

    private static String[] typeName=new String[]{"系统","None","年","一年365","日记","月","旬","日","单据","目录","参数表","帐套","汇总明细查询表","临时查询表","标志汇总表","单据目录"};

    /**
     * 根据Report类型获取中文类型名称
     * @param type
     * @return
     */
    public static String getReportTypeName(int type)
    {
        if(type<-1||type>14)type=0;
        return typeName[type+1];
    }

}
