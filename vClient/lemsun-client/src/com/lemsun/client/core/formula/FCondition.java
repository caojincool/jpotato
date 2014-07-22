package com.lemsun.client.core.formula;

import org.apache.commons.lang3.StringUtils;

/**
 * 连接 Express 与 Express 之间的逻辑值
 * User: 宗
 * Date: 13-4-22
 * Time: 下午2:12
 */
public enum FCondition {

    And,
    Or,
    Not,
    Start;

    public int getInt() {

        if(this == And) {
            return 1;
        }
        else if(this == Or) {
            return 2;
        }
        else if(this == Not) {
            return 3;
        }
        else if(this == Start) {
            return 4;
        }
        return -1;

    }

    public static FCondition parse(String condition) throws FormulaException {
        String m = condition.toLowerCase();

        if(StringUtils.equals(m, "&") || StringUtils.equals(m, "and")) {
            return FCondition.And;
        }
        else if(StringUtils.equals(m, "|") || StringUtils.equals(m, "or")) {
            return FCondition.Or;
        }
        else if(StringUtils.equals(m, "!") || StringUtils.equals(m, "not")) {
            return FCondition.Not;
        }

        throw new FormulaException("没有定义的逻辑操作符 " + condition);

    }

    public static String parseToString(FCondition condition){
        switch (condition) {
            case And:
                return "&";
            case Or:
                return "|";
            case Not:
                return "!";
            default:
                return "";
        }
    }


}
