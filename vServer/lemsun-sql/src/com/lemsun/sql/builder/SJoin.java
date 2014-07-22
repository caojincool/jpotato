package com.lemsun.sql.builder;

/**
 * 表格间的连接信息
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:38
 */
public class SJoin extends DefaultSQLSegment {


    private SJoinType joinType = SJoinType.Join;
    private SWhere on;

    public SJoin() {
    }

    public SJoin(SJoinType joinType) {
        this.joinType = joinType;
    }

    /**
     * 连接表格
     */
    public ISQLFormItem getFormItem()
    {
        return (ISQLFormItem) getParent();
    }


    public SJoinType getJoinType() {
        return joinType;
    }

    public SWhere getOn() {
        return on;
    }

    public void setOn(SWhere on) {

        if(this.on == on) return;
        if(this.on != null) this.on.remove();
        if(on != null) addChild(on);
        this.on = on;
    }

    /**
     * 标记当前的
     */
    public SJoin left()
    {
        joinType = SJoinType.Left;
        return this;
    }


    public SJoin right()
    {
        joinType = SJoinType.Right;
        return this;
    }

    public SJoin all()
    {
        joinType = SJoinType.JoinAll;
        return this;
    }

    public SJoin join()
    {
        joinType = SJoinType.Join;
        return this;
    }

    public SJoin on(SExpre expre) {
        if(getOn() == null) {
            setOn(new SWhere());
        }
        getOn().expr(expre);
        return this;
    }


    public SJoin on(SCol col, SOperator op, Object value)
    {
        return on(new SExpre().col(col).op(op).value(value));
    }


    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;
        if(item == on) on = null;
        return true;
    }


    @Override
    public void validate() throws SqlBuilderException {
        if(getOn() != null)
            getOn().validate();
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getJoinStatement(this);
    }
}
