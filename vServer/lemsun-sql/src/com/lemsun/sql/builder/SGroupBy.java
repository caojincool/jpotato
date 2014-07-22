package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合查询
 */
public class SGroupBy extends DefaultSQLSegment {

    private ArrayList<SCol> cols = new ArrayList<>();
    private SWhere having;

    public List<SCol> getCols() {
        return new ArrayList<>(cols);
    }

    public SWhere getHaving() {
        return having;
    }

    public void setHaving(SWhere having) {
        if(this.having == having) return;
        if(this.having != null) this.having.remove();
        if(having != null) addChild(having);
        this.having = having;
    }

    public SGroupBy col(SCol col) {
        addChild(col);
        cols.add(col);
        return this;
    }

    public SGroupBy col(String col) {
        return col(new SCol(col));
    }

    public SGroupBy having(SExpre expre) {
        if(getHaving() != null) setHaving(new SWhere());
        getHaving().expr(expre);
        return this;
    }

    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(super.removeChild(item)) return  false;

        if(item instanceof SCol) {
            cols.remove(item);
        }
        else if(item == having) {
            having = null;
        }
        return true;
    }

    @Override
    public void validate() throws SqlBuilderException {
        if(cols.size() == 0)
            throw new SqlBuilderException("Group by 的列不能为空");

        if(getHaving() != null) getHaving().validate();
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getGroupByStatement(this);
    }
}
