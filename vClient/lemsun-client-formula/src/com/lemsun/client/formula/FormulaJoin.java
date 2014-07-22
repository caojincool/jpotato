package com.lemsun.client.formula;

import com.lemsun.client.core.formula.FCondition;
import com.lemsun.client.core.formula.FOperater;
import com.lemsun.client.core.formula.IFormulaJoin;

/**
 * 公式的对象的关联模型
 */
public class FormulaJoin implements IFormulaJoin {
    private FCondition condition;
    private String leftRef;
    private String leftName;
    private String rightRef;
    private String rightName;
    private FOperater operater;

    public FormulaJoin(FCondition condition, String leftRef, String leftName, String rightRef, String rightName, FOperater operater) {
        this.condition = condition;
        this.leftRef = leftRef;
        this.leftName = leftName;
        this.rightRef = rightRef;
        this.rightName = rightName;
        this.operater = operater;
    }

    public FCondition getCondition() {
        return condition;
    }

    public String getLeftRef() {
        return leftRef;
    }

    public String getLeftName() {
        return leftName;
    }

    public String getRightRef() {
        return rightRef;
    }

    public String getRightName() {
        return rightName;
    }

    public FOperater getOperater() {
        return operater;
    }
}
