package com.lemsun.sql.builder;

import com.lemsun.core.formula.ISoft;

/**
 * 排序类型
 */
public enum SOrderType {
    Asc, Desc;
    public static SOrderType convert(ISoft soft){
        if( soft.getSoft() == ISoft.ASC){
            return SOrderType.Asc;
        }else{
            return SOrderType.Desc;
        }
    }

}
