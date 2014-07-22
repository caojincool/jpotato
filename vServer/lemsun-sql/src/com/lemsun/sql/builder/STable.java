package com.lemsun.sql.builder;

import org.apache.commons.lang3.StringUtils;

/**
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:34
 */
public class STable extends DefaultSQLSegment implements ISQLFormItem {

    private String name;
    private String alias;
    private SJoin join;

    public STable() {
    }

    public STable(String name) {
        this.name = name;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取当前表格跟其他表格连接条件
     */
    public SJoin getJoin() {
        return join;
    }


    public void setJoin(SJoin join) {

        if(this.join == join) return;
        if(this.join != null) this.join.remove();
        if(join != null) addChild(join);
        this.join = join;
    }

    /**
     * 表名
     */
    public STable name(String name)
    {
        setName(name);
        return this;
    }

    /**
     * 别名
     */
    public STable alias(String alias) {
        setAlias(alias);
        return this;
    }

    public STable on(SExpre ex) {
        if(getJoin() == null) {
            setJoin(new SJoin().on(ex));
        }
        else {
            getJoin().on(ex);
        }
        return this;
    }


    public STable join(SJoin join) {
        setJoin(join);
        return this;
    }


    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;
        if(item == join) join = null;
        return true;
    }


    @Override
    public void validate() throws SqlBuilderException {
        if (StringUtils.isEmpty(getName()))
            throw new SqlBuilderException("查询表格必须有 Name 属性");

        if (getJoin() != null)
            getJoin().validate();
    }

    @Override
    public String toSQL(ISQLAdapter adapter, boolean without) throws SqlBuilderException {
        return adapter.getTableStatement(this, false);
    }


    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getTableStatement(this, true);
    }
}
