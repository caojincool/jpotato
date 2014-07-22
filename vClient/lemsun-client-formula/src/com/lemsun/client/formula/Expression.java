package com.lemsun.client.formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lemsun.client.core.formula.FCondition;
import com.lemsun.client.core.formula.FOperater;
import com.lemsun.client.core.formula.IFExpression;
import com.lemsun.client.core.formula.IFormula;

/**
 * 公式中的表达式
 * User: 宗旭东
 * Date: 13-4-24
 * Time: 下午2:15
 */
public class Expression implements IFExpression {


    private FCondition condition;
    private FOperater operater;
    private String var;
    private String ref;
    private Object value;
    private Expression expression;
    private IFormula formula;
    private IFormula formulaValue;


    /**
     * 使用表格默认的编码列进行提取数据
     */
    public Expression(Object value) {
        this(null, null, null, FOperater.Eq, value, null);
    }

    /**
     * 使用表达式进行提取数据
     */
    public Expression(String var, FOperater operater, Object value) {
        this(null, null, var, operater, value, null);
    }

    /**
     * 全局的构造
     */
    public Expression(FCondition condition, String ref, String var, FOperater operater, Object value, Expression nextExpression) {
        this.var = var;
        this.operater = operater;
        this.ref = ref;
        setValue(value);
        this.condition = condition;
        this.expression = nextExpression;
    }

    /**
     * 获取引用名称
     */
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * 获取操作列
     */
    public String getVar() {
        return var;
    }

    @JsonIgnore
    public FCondition getCondition() {
        return condition;
    }

    @JsonProperty("condition")
    public int getConditionInt() {
        return getCondition() == null ? -1 : getCondition().getInt();
    }

    public void setCondition(FCondition condition) {
        this.condition = condition;
    }

    /**
     * 获取操作值
     */
    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {

        if(value instanceof IFormula) {
            setFormulaValue((IFormula)value);
        }
        else {
            this.value = value;
        }

    }

    /**
     * 获取值与列的操作符
     */
    @JsonIgnore
    public FOperater getOperater() {
        return operater;
    }

    @JsonProperty("operater")
    public int getOperaterInt() {
        return getOperater() == null ? -1 : getOperater().getInt();
    }

    @JsonIgnore
    public IFormula getFormulaValue() {
        return formulaValue;
    }

    public void setFormulaValue(IFormula formulaValue) {
        this.formulaValue = formulaValue;
    }

    /**
     * 把操作符枚举转换成操作符号
     */
    @JsonIgnore
    public String getOperaterToString(){
        return FOperater.parseToString(operater);
    }

    /**
     * 获取逻辑符符号
     */
    @JsonIgnore
    public String getConditionToString(){
        return FCondition.parseToString(condition);
    }
    /**
     * 获取公式
     */
    @JsonIgnore
    public IFormula getFormula() {
        return formula;
    }

    /**
     * 设置公式
     */
    public void setFormula(IFormula formula) {
        this.formula = formula;
    }

    /**
     * 获取下一个的表达式
     */
    public Expression getNextExpression() {
        return expression;
    }

    /**
     * 设置下一个表达式
     */
    public void setNextExpression(Expression expression) {
        this.expression = expression;
    }
}
