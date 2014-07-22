package com.lemsun.client.core.formula;

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
     * 非 模糊查询
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


    public int getInt() {

        if(this == Eq) {
            return 1;
        }
        else if(this == Less) {
            return 2;
        }
        else if(this == Greater) {
            return 3;
        }
        else if(this == GEq) {
            return 4;
        }
        else if(this == LEq) {
            return 5;
        }
        else if(this == UnEq) {
            return 6;
        }
        else if(this == Like) {
            return 7;
        }
        else if(this == UnLike) {
            return 8;
        }
        else if(this == In) {
            return 9;
        }
        else if(this == UnIn) {
            return 10;
        }
        return 1;
    }


    public static FOperater parse(String operater) throws FormulaException {

        operater = operater.toLowerCase();

        if(StringUtils.equals(operater, "=")) {
            return FOperater.Eq;
        } else if(StringUtils.equals(operater, ">")) {
            return FOperater.Greater;
        } else if(StringUtils.equals(operater, "<")) {
            return FOperater.Less;
        } else if(StringUtils.equals(operater, ">=")) {
            return FOperater.GEq;
        } else if(StringUtils.equals(operater, "<=")) {
            return FOperater.LEq;
        } else if(StringUtils.equals(operater, "!=") || StringUtils.equals(operater, "<>")) {
            return FOperater.UnEq;
        } else if(StringUtils.equals(operater, "~") || StringUtils.equals(operater, "like")) {
            return FOperater.Like;
        } else if(StringUtils.equals(operater, "!~") || StringUtils.equals(operater, "unlike")) {
            return FOperater.UnLike;
        } else if(StringUtils.equals(operater, "in")) {
            return FOperater.In;
        } else if(StringUtils.equals(operater, "unin")) {
            return FOperater.UnIn;
        }
        throw new FormulaException("操作符没有实现 : " + operater);
    }

    public static String parseToString(FOperater operater){
        switch (operater) {
            case Eq:
                return "=";
            case Less:
                return "<";
            case Greater:
                return ">";
            case GEq:
                return ">=";
            case LEq:
                return "<=";
            case UnEq:
                return "!=";
            case Like:
                return "~";
            case UnLike:
                return "!~";
            case In:
                return "in";
            case UnIn:
                return "unin";
            default:
                return "";
        }
    }


}
