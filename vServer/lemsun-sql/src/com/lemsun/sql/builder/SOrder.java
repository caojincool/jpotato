package com.lemsun.sql.builder;

/**
 * 排序片段
 */
public class SOrder extends DefaultSQLSegment {

    private SOrderType type;
    private SCol col;

    public SOrder(SCol col,SOrderType type) {
        this.col = col;
        this.type=type;
    }

    public SOrder() {

    }

    public SOrderType getType() {
        return type;
    }

    public void setType(SOrderType type) {
        this.type = type;
    }

    public SCol getCol() {
        return col;
    }

    public void setCol(SCol col) {
        if(this.col == col) return;
        if(this.col != null) this.col.remove();
        if(col != null) addChild(col);
        this.col = col;
    }


    public SOrder col(SCol col) {
        setCol(col);
        return this;
    }

    public SOrder col(String col) {
        return col(new SCol(col));
    }

    public SOrder type(SOrderType type) {
        setType(type);
        return this;
    }

    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(super.removeChild(item)) return false;
        this.col = null;
        return true;
    }

    @Override
    public void validate() throws SqlBuilderException {
        if(getCol() == null) throw new SqlBuilderException("排序必须要有列名");
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getOrderStatement(this);
    }
}
