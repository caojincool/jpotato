package com.lemsun.core.formula;

import org.apache.commons.lang3.StringUtils;

/**
 * 操作类
 * User: 宗
 * Date: 13-4-22
 * Time: 上午9:59
 */
public enum FOperater {

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
     * 小于等于
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


    public static FOperater parse(int operater) {

        FOperater o = FOperater.Eq;

        if (operater == 1) {
            o = FOperater.Eq;
        } else if (operater == 2) {
            o = FOperater.Less;
        } else if (operater == 3) {
            o = FOperater.Greater;
        } else if (operater == 4) {
            o = FOperater.GEq;
        } else if (operater == 5) {
            o = FOperater.LEq;
        } else if (operater == 6) {
            o = FOperater.UnEq;
        } else if(operater == 7) {
            o = FOperater.Like;
        } else if(operater == 8) {
            o = FOperater.UnLike;
        } else if(operater == 9) {
            o = FOperater.In;
        } else if(operater == 10) {
            o = FOperater.UnIn;
        }

        return o;
    }

    public static FOperater parse(String operater) throws FormulaException {

        operater = operater.toLowerCase();

        if (StringUtils.equals(operater, "=")) {
            return FOperater.Eq;
        } else if (StringUtils.equals(operater, ">")) {
            return FOperater.Greater;
        } else if (StringUtils.equals(operater, "<")) {
            return FOperater.Less;
        } else if (StringUtils.equals(operater, ">=")) {
            return FOperater.GEq;
        } else if (StringUtils.equals(operater, "<=")) {
            return FOperater.LEq;
        } else if (StringUtils.equals(operater, "!=") || StringUtils.equals(operater, "<>")) {
            return FOperater.UnEq;
        } else if(StringUtils.equals(operater, "~") || StringUtils.equals(operater, "like ")) {
            return FOperater.Like;
        } else if(StringUtils.equals(operater, "!~") || StringUtils.equals(operater, "unlike ")) {
            return FOperater.UnLike;
        } else if(StringUtils.equals(operater, "in ")) {
            return FOperater.In;
        } else if(StringUtils.equals(operater, "unin ")) {
            return FOperater.UnIn;
        }

        throw new FormulaException("操作符没有实现 : " + operater, "001");
    }

    /**
     * 给出SQL的操作符
     */
    public String toSQL()
    {
        return this == FOperater.Eq ? "="
                : this == FOperater.GEq ? ">="
                : this == FOperater.Greater ? ">"
                : this == FOperater.LEq ? "<="
                : this == FOperater.Less ? "<"
                : this == FOperater.Like ? "Like"
                : this == FOperater.UnLike ? "not Like"
                : this == FOperater.In ? "in"
                : this == FOperater.UnIn ? "not in"
                : "<>";
    }

}
