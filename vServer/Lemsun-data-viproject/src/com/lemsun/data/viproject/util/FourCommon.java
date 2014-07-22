package com.lemsun.data.viproject.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-11
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
public class FourCommon {

    /**
     * 表名格式分割字符串
     */
    public static final String a26spchar="!@#";

    /**
     * 获取4代树显示中的表名
     * @return
     */
    public static String getFourTableName(String code,Date date,String guize)
    {
        String baseTableName= "R_"+code.replace(".","_");
        if(!StringUtils.isEmpty(guize))
        {
            baseTableName+=guize.toUpperCase() .replace("%N","_"+getYear(date.getYear())).replace("%M","_"+getMonth(date.getMonth())).replace("%D","_"+getDate(date.getDate()));
        }

        String name = String.format("00", 1);

        return baseTableName;
    }

    public static String getYear(int year)
    {
        return String.valueOf(year+1900);
    }
    public static String getMonth(int month)
    {
        if(month>8)
            return String.valueOf(month+1);


        return  "0"+String.valueOf(month+1);
    }

    public static String getDate(int date)
    {
        if(date>9)
            return String.valueOf(date);
        return "0"+String.valueOf(date);
    }


    public static String getClientNameByA26(String a26)
    {
        String[] a26sp= a26.split(a26spchar);
        if(a26sp.length>=4&&a26sp[3].toLowerCase().equals("true")){
            return a26sp[4];
        }
        return "";
    }

}
