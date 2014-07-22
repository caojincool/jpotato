package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作语句
 * User: 宗旭东
 * Date: 14-3-3
 * Time: 下午3:22
 */
public class SSetQuery extends DefaultSQLSegment implements ISQLStatement {

    private final ArrayList<SCol> cols = new ArrayList<>();
    private ISQLFormItem form;
    private SWhere where;
    private int index;
    private ArrayList<SParam> params;


    public ISQLFormItem getForm() {
        return form;
    }

    public void setForm(ISQLFormItem form) {
        if(this.form == form) return;
        if(this.form != null) this.form.remove();
        if(form != null) addChild(form);
        this.form = form;
    }

    public SWhere getWhere() {
        return where;
    }

    public void setWhere(SWhere where) {
        if(this.where == where) return;
        if(this.where != null) this.where.remove();
        if(where != null) addChild(where);
        this.where = where;
    }

    public List<SCol> getCols()
    {
        return new ArrayList<>(cols);
    }


    public void addCol(SCol col) {
        addChild(col);
        cols.add(col);
    }

    public SSetQuery col(SCol col) {
        addCol(col);
        return this;
    }

    @Override
    public int nextIndex() {
        return ++index;
    }

    @Override
    public List<SParam> getParamters() {
        if(getParent() != null) {
            return getStatement().getParamters();
        }
        else {
            if(params != null) return params;

            List<ISQLSegment> segments = getAllSegments();
            params = new ArrayList<>();
            if(segments != null) {
                for(ISQLSegment s : segments) {
                    if(s instanceof SParam) {
                        SParam p = (SParam)s;
                        if(p.isParameter()) params.add(p);
                    }
                }
            }

            return params;
        }
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        index = -1;
        params =  null;
        return super.toSQL(adapter);
    }
}
