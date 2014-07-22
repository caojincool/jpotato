package com.lemsun.formula;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lemsun.core.formula.FCondition;
import com.lemsun.core.formula.FOperater;
import com.lemsun.core.formula.IFExpression;
import com.lemsun.core.formula.IFormula;
import com.lemsun.data.lmstable.Column;
import org.apache.commons.lang3.StringUtils;

/**
 * 公式中的表达式
 * User: 宗
 * Date: 13-4-21
 * Time: 下午7:11
 */
public class FExpression implements IFExpression {

    private String var;
    private String ref;
    private Object value;
    private FOperater operater;
    private FCondition condition;
    private FExpression expression;
    private IFormula formula;
    private Column col;
    private IFormula formulaValue;



    /**
     * 构造一个空得表达式, 后期再来添加属性
     */
    public FExpression() {

    }

    /**
     * 使用表格默认的编码列进行提取数据
     */
    public FExpression(Object value) {
        this(null, null, FOperater.Eq, value, null);
    }

    /**
     * 使用表达式进行提取数据
     */
    public FExpression(String var, FOperater operater, Object value) {
        this(null, var, operater, value, null);
    }

    /**
     * 全局的构造
     */
    public FExpression(FCondition condition, String var, FOperater operater, Object value, FExpression nextExpression) {
        this.var = var;
        this.operater = operater;
        this.value = value;
        this.condition = condition;
        this.expression = nextExpression;
    }


    public String getRef() {
        if(StringUtils.isNotEmpty(ref)){
            return ref.toUpperCase();
        }
        return ref;
    }


    /**
     * 获取操作列
     */
    public String getVar() {
        if(StringUtils.isEmpty(var)){
            return "code";
        }
        return var;
    }

    /**
     * 获取操作值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 获取值与列的操作符
     */
    public FOperater getOperater() {
        return operater;
    }

    /**
     * 获取公式
     */
    public IFormula getFormula() {
        return formula;
    }

    /**
     * 获取表达式对应的表格
     */
    public Column getCol() {
        return col;
    }


    public void setVar(String var) {
        this.var = var;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setOperater(FOperater operater) {
        this.operater = operater;
    }

    @JsonProperty
    public void setOperater(int operater) {
        this.operater = FOperater.parse(operater);
    }

    public FCondition getCondition() {
        return condition;
    }

    public void setCondition(FCondition condition) {
        this.condition = condition;
    }

    @JsonProperty
    public void setCondition(int condition) {

        if (condition == 1) {
            this.condition = FCondition.And;
        } else if (condition == 2) {
            this.condition = FCondition.Or;
        } else if (condition == 3) {
            this.condition = FCondition.Not;
        } else if (condition == 4) {
            this.condition = FCondition.Start;
        }

    }

    public void setCol(Column col) {
        this.col = col;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }


    public IFormula getFormulaValue() {
        return formulaValue;
    }

    public void setFormulaValue(IFormula formulaValue) {
        this.formulaValue = formulaValue;
    }

    /**
     * 设置公式
     */
    public void setFormula(IFormula formula) {
        this.formula = formula;
        if (getNextExpression() != null) getNextExpression().setFormula(formula);
    }

    /**
     * 获取下一个的表达式
     */
    public FExpression getNextExpression() {
        return expression;
    }

    /**
     * 设置下一个表达式
     */
    public void setNextExpression(FExpression expression) {
        this.expression = expression;
    }


}
