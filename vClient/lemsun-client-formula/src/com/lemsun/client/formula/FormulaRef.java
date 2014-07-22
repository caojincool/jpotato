package com.lemsun.client.formula;

import com.lemsun.client.core.formula.IFormulaJoin;
import com.lemsun.client.core.formula.IFormulaRef;

/**
 * 公式引用模型
 * Created by xudong on 14-1-21.
 */
public class FormulaRef implements IFormulaRef {

    private String name;
    private String alias;
    private int joinState;
    private IFormulaJoin[] joins;

    public FormulaRef(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public FormulaRef(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取别名
     */
    public String getAlias() {
        return alias;
    }

    @Override
    public int getJoinState() {
        return joinState;
    }



    @Override
    public IFormulaJoin[] getJoins() {
        return joins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJoinState(int joinState) {
        this.joinState = joinState;
    }

    public void setJoins(IFormulaJoin[] joins) {
        this.joins = joins;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
