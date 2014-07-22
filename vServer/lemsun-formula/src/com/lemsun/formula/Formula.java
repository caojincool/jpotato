package com.lemsun.formula;

import com.lemsun.core.formula.IFExpression;
import com.lemsun.core.formula.IFormula;
import com.lemsun.core.formula.IFormulaRef;
import com.lemsun.core.formula.ISoft;
import com.lemsun.ldbc.ITableResource;

/**
 * 公式的模型对象 <br/>
 * <p/>
 * 一个公式表达了一个组件的取数方式.
 * 公式必须给出获取组件的 ID
 * <p/>
 * User: 宗
 * Date: 13-4-21
 * Time: 下午5:31
 */
public class Formula implements IFormula {


    private String adate;



    private FColRange colRange;

    private FExpression expression;

    private String input;
    private IFormulaRef[] refs;
    private ISoft[] softs;


    public Formula( FColRange range, FExpression expression) {

        this.colRange = range;

        if (expression != null)
            expression.setFormula(this);

        this.expression = expression;
    }


    public Formula(IFormulaRef[] refs, FColRange range, FExpression expression, ISoft[] softs)
    {
        this.refs = refs;
        this.colRange = range;
        this.expression = expression;
        this.softs = softs;


        if(this.expression != null)
            this.expression.setFormula(this);

    }


    public Formula()
    {

    }

    /**
     * 获取公式录入的原始表达式
     * @return
     */
    public String getInput() {
        return input;
    }

    /**
     * 设置公式的原始表达式
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * 获取公式的操作日期
     */
    @Override
    public String getAdate() {
        return adate;
    }

    /**
     * 设置公式的操作日期
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }



    @Override
    public IFormulaRef[] getRefs() {
        return this.refs;
    }

    /**
     * 获取显示列表
     */
    @Override
    public FColRange getColRange() {
        return colRange;
    }

    /**
     * 获取数据条件
     */
    @Override
    public IFExpression getExpression() {
        return expression;
    }

    @Override
    public ISoft[] getSofts() {
        return this.softs;
    }


    public void setColRange(FColRange colRange) {
        this.colRange = colRange;
    }

    public void setExpression(FExpression expression) {
        this.expression = expression;
    }

    public void setRefs(FormulaRef[] refs) {
        this.refs = refs;
    }

    public void setSofts(Soft[] softs) {
        this.softs = softs;
    }

}
