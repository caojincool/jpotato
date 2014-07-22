package com.lemsun.sql.builder;

/**
 * 条件表达式
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:48
 */
public class SExpre extends DefaultSQLSegment {

    private SExpre nextExpre;
    private SCol col;
    private ISQLValue value;
    private SOperator operator;
    private SCondition condition;

    public SExpre(SCol col, ISQLValue value, SOperator operator) {
        setCol(col);
        setValue(value);
        setOperator(operator);
    }


    public SExpre() {
    }

    /**
     * 设置获取当前的条件与之前的表达式的逻辑判断情况
     */
    public SCondition getCondition() {
        return condition;
    }

    /**
     * 设置获取当前的条件与之前的表达式的逻辑判断情况
     */
    public void setCondition(SCondition condition) {
        this.condition = condition;
    }

    /**
     * 设置获取操作符
     */
    public SOperator getOperator() {
        return operator;
    }

    /**
     * 设置获取操作符
     */
    public void setOperator(SOperator operator) {
        this.operator = operator;
    }

    /**
     * 获取设置下一个表达式
     */
    public SExpre getNextExpre() {
        return nextExpre;
    }

    /**
     * 获取设置下一个表达式
     */
    public void setNextExpre(SExpre nextExpre) {

        if(this.nextExpre == nextExpre) return;
        if(this.nextExpre != null) this.nextExpre.remove();
        if(nextExpre != null) addChild(nextExpre);

        this.nextExpre = nextExpre;
    }

    /**
     *   设置获取表达式操作列
     */
    public SCol getCol() {
        return col;
    }

    /**
     *   设置获取表达式操作列
     */
    public void setCol(SCol col) {

        if(this.col == col) return;
        if(this.col != null) this.col.remove();
        if(col != null) addChild(col);

        this.col = col;
    }

    /**
     * 设置获取当前的表达式值
     */
    public ISQLValue getValue() {
        return value;
    }

    /**
     * 设置获取当前的表达式值
     */
    public void setValue(ISQLValue value) {

        if(this.value == value) return;
        if(this.value != null) this.value.remove();
        if(value != null) addChild(value);
        this.value = value;
    }


    public SExpre condition(SCondition condition) {
        setCondition(condition);
        return this;
    }

    public SExpre expre(SExpre expre)
    {
        setNextExpre(expre);
        return this;
    }

    public SExpre and()
    {
        condition = SCondition.And;
        return this;
    }

    public SExpre or()
    {
        condition = SCondition.Or;
        return this;
    }

    public SExpre col(SCol col)
    {
        setCol(col);
        return this;
    }

    public SExpre op(SOperator op)
    {
        operator = op;
        return this;
    }

    public SExpre value(ISQLValue value)
    {
        setValue(value);
        return this;
    }

    public SExpre value(Object value) {
        return value(new SParam(value));
    }

    /**
     * 从当前片段中移除给定的节点, 考虑到节点可以自己删除. 父节点需要重写这个方法. 同步设置成 null
     */
    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;

        if(item == this.col) {
            this.col = null;
        }
        else if(item == nextExpre) {
            nextExpre = null;
        }
        else if(item == value) {
            value = null;
        }
        return true;
    }

    @Override
    public void validate() throws SqlBuilderException {
        if (getValue() == null)
            throw new SqlBuilderException("表达式的值不能为空");

        if (getCol() == null)
            throw new SqlBuilderException("表达式的列不能为空");
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getExpreStatement(this);
    }
}
