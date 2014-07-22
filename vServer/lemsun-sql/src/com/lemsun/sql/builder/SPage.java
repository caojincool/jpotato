package com.lemsun.sql.builder;

/**
 * 分页模型
 */
public class SPage extends DefaultSQLSegment {

    private ISQLValue start;
    private ISQLValue limit;


    public SPage(int start, int limit) {
        this.start = new SParam(start);
        this.limit = new SParam(limit);
    }


    public SPage() {

    }

    public SSelect getSelect() {
        return (SSelect) getParent();
    }

    public ISQLValue getStart() {
        return start;
    }

    public void setStart(ISQLValue start) {
        if(this.start == start) return;
        if(this.start != null) this.start.remove();
        if(start != null) addChild(start);
        this.start = start;
    }

    public ISQLValue getLimit() {
        return limit;
    }

    public void setLimit(ISQLValue limit) {
        if(this.limit == limit) return;
        if(this.limit != null) this.limit.remove();
        if(limit != null) addChild(limit);
        this.limit = limit;
    }


    public SPage start(int start) {
        return start(new SParam(start));
    }

    public SPage start(ISQLValue start)
    {
        setStart(start);
        return this;
    }

    public SPage limit(int limit) {
        return limit(new SParam(limit));
    }

    public SPage limit(ISQLValue value) {
        setLimit(value);
        return this;
    }

    /**
     * 返回确定是否有分页数据.
     */
    public boolean isPage() {
        return start != null && limit != null;
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {

        return adapter.getPageStatement(this);

    }
}
