package com.lemsun.core.formula;

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

    public static FCondition parse(String condition) throws FormulaException {
        String m = condition.toLowerCase();

        if (StringUtils.equals(m, "&") || StringUtils.equals(m, "and")) {
            return FCondition.And;
        } else if (StringUtils.equals(m, "|") || StringUtils.equals(m, "or")) {
            return FCondition.Or;
        } else if (StringUtils.equals(m, "!") || StringUtils.equals(m, "not")) {
            return FCondition.Not;
        }

        throw new FormulaException("没有定义的逻辑操作符 " + condition, "001");

    }


    public static FCondition parseInt(int condition) {
        FCondition c = FCondition.Start;

        if (condition == 1) {
            c = FCondition.And;
        } else if (condition == 2) {
            c = FCondition.Or;
        } else if (condition == 3) {
            c = FCondition.Not;
        } else if (condition == 4) {
            c = FCondition.Start;
        }
        return c;
    }


    /**
     * 转换成SQL的逻辑符
     * @return
     */
    public String toSQL()
    {
        return this == Start ? "" : this == And ? "AND" : this == Or ? "OR" : "NOT";
    }

}
