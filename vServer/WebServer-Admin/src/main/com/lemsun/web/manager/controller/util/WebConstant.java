package com.lemsun.web.manager.controller.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供常量工具类
 * User: 刘晓宝
 * Date: 13-9-23
 * Time: 上午10:19
 */
public class WebConstant {
    public static final  String YYYYMMDDHHMMSS="yyyyMMddhhmmss";
    public static final String ISNEXT="1";//代表下一步
    public static final int IMAGE_80X80=80;//图片尺寸长宽都是80
    public static final int IMAGE_150X150=150;//图片尺寸长宽都是150
    public static final int IMAGE_W_1000=950;//固定宽度950
    public static final Map<Integer,String> tableCategoryMap=new HashMap<>();//表类型
    public static final Map<Integer,String> columnCategoryMap=new HashMap<>();//数据库表字段类型
    public static final String IMGROOT = "uploads"+File.separator;
    static {
        columnCategoryMap.put(-1,"外键");
        columnCategoryMap.put(1,"文本字段");
        columnCategoryMap.put(2,"整数");
        columnCategoryMap.put(3,"小数");
        columnCategoryMap.put(4,"数据");
        columnCategoryMap.put(5,"时间");
        columnCategoryMap.put(6,"多媒体");
        columnCategoryMap.put(7,"逻辑");
        columnCategoryMap.put(8,"表格主键");
        columnCategoryMap.put(9,"更新时间标记");
        columnCategoryMap.put(10,"固定编码");
        columnCategoryMap.put(11,"HTML");
        columnCategoryMap.put(12,"图片");
        columnCategoryMap.put(13,"文件集");
        columnCategoryMap.put(14,"XML");
        columnCategoryMap.put(15,"操作日期");
    }
    static {
        tableCategoryMap.put(0,"未知表");
        tableCategoryMap.put(1,"年表");
        tableCategoryMap.put(2,"一年365表");
        tableCategoryMap.put(3,"日记录表");
        tableCategoryMap.put(4,"月表");
        tableCategoryMap.put(5,"旬表");
        tableCategoryMap.put(6,"日表");
        tableCategoryMap.put(7,"单据表");
        tableCategoryMap.put(8,"目录表");
        tableCategoryMap.put(9,"参数表");
        tableCategoryMap.put(10,"账套");
        tableCategoryMap.put(11,"汇总表");
        tableCategoryMap.put(12,"临时表");
        tableCategoryMap.put(13,"标识汇总表");
        tableCategoryMap.put(14,"单据目录表");
    }
}
