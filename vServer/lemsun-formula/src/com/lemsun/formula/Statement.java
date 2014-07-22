package com.lemsun.formula;

import com.lemsun.core.formula.FOperater;
import com.lemsun.core.formula.IFormula;
import com.lemsun.core.formula.IStatement;

/**
 * 公式负责比较的语句
 * <p/>
 * User: 宗旭东
 * Date: 13-9-9
 * Time: 上午10:51
 */
public class Statement implements IStatement {

    private IFormula fleft;
    private IFormula fright;
    private FOperater operater;

    /**
     * 获取表达式左边的赋值公式
     */
    public IFormula getFleft() {
        return fleft;
    }

    /**
     * 设置表达式左边的公式
     */
    public void setFleft(Formula fleft) {
        this.fleft = fleft;
    }

    /**
     * 获取表达式右边的公式
     */
    public IFormula getFright() {
        return fright;
    }

    /**
     * 设置表达式右边的公式
     *
     * @param fright
     */
    public void setFright(Formula fright) {
        this.fright = fright;
    }

    /**
     * 操作符合
     */
    public FOperater getOperater() {
        return operater;
    }

    /**
     * 设置操作符
     */
    public void setOperater(FOperater operater) {
        this.operater = operater;
    }
}
