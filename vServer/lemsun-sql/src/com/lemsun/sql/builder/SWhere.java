package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件集合对象
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:45
 */
public class SWhere extends DefaultSQLSegment {

    private final ArrayList<SExpre> expres = new ArrayList<>();

    public SWhere() {
    }

    public List<SExpre> getExpres() {
        return new ArrayList<>(expres);
    }


    public SWhere expr(SExpre expre) {
        addChild(expre);
        expres.add(expre);
        return this;
    }


    /**
     * 列相同的条件
     */
    public SWhere and(String col, SOperator op, String targetCol) {
        return and(new SCol(col), op, new SCol(targetCol));
    }

    public SWhere and(String col, SOperator op, Object value) {
        return and(new SCol(col), op, new SParam(value));
    }

    /**
     * 添加一个 AND 条件
     */
    public SWhere and(SCol col, SOperator op, ISQLValue value)
    {
        SExpre expre = new SExpre(col, value, op);
        expre.setCondition(SCondition.And);
        return expr(expre);
    }

    /**
     * 添加一个 OR 条件
     */
    public SWhere or(SCol col, SOperator op, ISQLValue value)
    {
        SExpre expre = new SExpre(col, value, op);
        expre.setCondition(SCondition.Or);
        return expr(expre);
    }


    public SWhere removeExpr(SExpre expre) {
        removeChild(expre);
        return this;
    }

    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;
        expres.remove((SExpre)item);
        return true;
    }

    @Override
    public void validate() throws SqlBuilderException {

        if(expres.size() == 0) throw new SqlBuilderException("条件语句不能没有条件对象");
        expres.get(0).setCondition(SCondition.Start);
        for(SExpre e : expres) {
            e.validate();
        }
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getWhereStatement(this);
    }
}
