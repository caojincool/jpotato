package com.lemsun.client.formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.client.core.formula.*;

/**
 * 客户端公式模型对象
 * User: 宗
 * Date: 13-4-23
 * Time: 下午7:16
 */
public class Formula implements IFormula {

    private String input;
    private IFColRange colRange;
    private IFExpression expression;
    private IFormulaRef[] refs;
    private ISoft[] softs;
    private String adate;
    private boolean isRemote;



    public Formula(String input, String ref) {
        this(input, false, new FormulaRef[] { new FormulaRef(ref) }, null, null, null);
    }

    public Formula(String input, boolean isRemote, IFormulaRef[] refs, IFColRange colRange, IFExpression expression, ISoft[] softs)
    {
        this.input = input;
        this.refs = refs;
        this.isRemote = isRemote;
        this.colRange = colRange;
        this.expression = expression;
        this.softs = softs;
    }


    /**
     * 获取是否是远程公式
     */
    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean isRemote) {
        this.isRemote = isRemote;
    }

    /**
     * 获取操作日期
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 设置操作日期
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }

    /**
     * 获取公式的输入字符串
     */
    public String getInput() {

        return input;
    }


    public IFormulaRef[] getRefs() {
        return refs;
    }

    /**
     * 设置引用
     */
    public void setRefs(FormulaRef[] refs) {
        this.refs = refs;
    }

    /**
     * 获取公式的引用对象
     */
    @JsonIgnore
    public String getRef() {
        return getRefs()[0].getName();
    }

    /**
     * 获取列集合
     */
    public IFColRange getColRange() {
        return colRange;
    }

    /**
     * 设置列集合
     */
    public void setColRange(ColRange colRange) {
        this.colRange = colRange;
    }

    /**
     * 获取表达式
     */
    public IFExpression getExpression() {
        return expression;
    }

    /**
     * 设置表达式
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * 获取排序信息
     */
    public ISoft[] getSofts() {
        return softs;
    }

    /**
     * 设置排序
     */
    public void setSofts(FSoft[] softs) {
        this.softs = softs;
    }
}
