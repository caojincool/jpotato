package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个参数集合
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午4:17
 */
public class SParamArray extends DefaultSQLSegment implements ISQLValue {

    private final ArrayList<ISQLValue> values = new ArrayList<>();


    public SParamArray() {
    }

    public SParamArray(ISQLValue[] values)
    {
        for(ISQLValue v : values)
        {
            add(v);
        }
    }

    /**
     * 获取当前的全部数据
     */
    public List<ISQLValue> getValues() {
        return new ArrayList<>(values);
    }


    public SParamArray add(ISQLValue value)
    {
        addChild(value);
        return this;
    }


    public SParamArray add(Object value)
    {
        return add(new SParam(value));
    }

    public SParamArray remove(ISQLValue value)
    {
        removeChild(value);
        return this;
    }

    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) {
            return false;
        }

        values.remove((ISQLValue)item);
        return true;
    }

    @Override
    protected void addChild(ISQLSegment item) {

        if(item == null || !(item instanceof ISQLValue))
        {
            return;
        }
        super.addChild(item);
        values.add((ISQLValue)item);
    }

    @Override
    public void validate() throws SqlBuilderException {
        if(values.size() > 0) return;
        throw new SqlBuilderException("数组参数的数值不能为空");
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getParamArrayStatement(this);
    }
}
