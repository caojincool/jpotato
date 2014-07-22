package com.lemsun.sql.builder;

import com.lemsun.core.formula.FOperater;

/**
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:55
 */
public enum SOperator {
    /**
     * 等于
     */
    Eq,

    /**
     * 小于
     */
    Less,

    /**
     * 大于
     */
    Greater,

    /**
     * 大于等于
     */
    GEq,

    /**
     *  小于等于
     */
    LEq,

    /**
     * 不等于
     */
    UnEq,

    /**
     * 模糊查询
     */
    Like,

    /**
     * 模糊查询取反
     */
    UnLike,

    /**
     * 包含
     */
    In,

    /**
     * 不包含
     */
    UnIn;

    /**
     * 转化
     * @param operater
     * @return
     */
    public static SOperator convert(FOperater operater){
        SOperator o = SOperator.Eq;

        if (operater == FOperater.Eq) {
            o = SOperator.Eq;
        } else if (operater == FOperater.Less) {
            o = SOperator.Less;
        } else if (operater == FOperater.Greater) {
            o = SOperator.Greater;
        } else if (operater == FOperater.GEq) {
            o = SOperator.GEq;
        } else if (operater == FOperater.LEq) {
            o = SOperator.LEq;
        } else if (operater == FOperater.UnEq) {
            o = SOperator.UnEq;
        } else if(operater == FOperater.Like) {
            o = SOperator.Like;
        } else if(operater == FOperater.UnLike) {
            o = SOperator.UnLike;
        } else if(operater == FOperater.In) {
            o = SOperator.In;
        } else if(operater == FOperater.UnIn) {
            o = SOperator.UnIn;
        }

        return o;
    }
}
