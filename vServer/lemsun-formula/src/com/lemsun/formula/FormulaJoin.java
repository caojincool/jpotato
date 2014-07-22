package com.lemsun.formula;

import com.lemsun.core.formula.*;
import com.lemsun.core.formula.FCondition;
import com.lemsun.core.formula.FOperater;
import org.apache.commons.lang3.StringUtils;

/**
 * 组件连接条件
 * User: 宗旭东
 * Date: 14-1-21
 * Time: 下午3:19
 */
public class FormulaJoin implements IFormulaJoin {
    private FCondition condition;
    private String leftRef;
    private String leftName;
    private String rightRef;
    private String rightName;
    private FOperater operater;

    public FCondition getCondition() {
        return condition;
    }

    public void setCondition(FCondition condition) {
        this.condition = condition;
    }

    public String getLeftRef() {
        if(StringUtils.isNotEmpty(leftRef)){
            return leftRef.toUpperCase();
        }
        return leftRef;
    }

    public void setLeftRef(String leftRef) {
        this.leftRef = leftRef;
    }

    public String getLeftName() {
        return leftName;
    }

    @Override
    public FOperater getOperater() {
        return operater;
    }


    public void setOperater(FOperater operater) {
        this.operater = operater;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getRightRef() {
        if(StringUtils.isNotEmpty(rightRef)){
            return rightRef.toUpperCase();
        }
        return rightRef;
    }

    public void setRightRef(String rightRef) {
        this.rightRef = rightRef;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }
}
