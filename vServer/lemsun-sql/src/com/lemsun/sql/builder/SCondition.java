package com.lemsun.sql.builder;

import com.lemsun.core.formula.FCondition;

/**
 * 连接条件
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午4:03
 */
public enum  SCondition {
    And, Or, Not, Start;

    /**
     * 公式里条件对象转化为sql对象条件对象
     * @param fCondition
     * @return
     */
    public static SCondition convert(FCondition fCondition){
        SCondition sCondition=SCondition.And;
        if (fCondition == FCondition.And) {
            sCondition = SCondition.And;
        } else if (fCondition == FCondition.Not) {
            sCondition = SCondition.Not;
        } else if (fCondition == FCondition.Or) {
            sCondition = SCondition.Or;
        } else if (fCondition == FCondition.Start) {
            sCondition = SCondition.Start;
        }
        return sCondition;
    }
}
