package com.lemsun.sql.builder;

import org.apache.commons.lang3.StringUtils;

/**
 * 列
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:08
 */
public class SCol extends DefaultSQLSegment implements ISQLCol, ISQLValue {

    private String name;
    private String formItem;
    private String alias;
    private String fun;

    public SCol(String name) {
        this.name = name;
    }

    public SCol(String name,  String formItem, String fun,String alias) {
        this.name = name;
        this.formItem=formItem;
        this.alias = alias;
        this.fun = fun;
    }
    public SCol(String name,  String formItem, String fun) {
        this.name = name;
        this.formItem=formItem;
        this.fun = fun;
    }
    public SCol() {
    }

    /**
     * 获取列名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置列名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取引用的操作对象
     */
    public String getFormItem() {
        return formItem;
    }

    /**
     * 设置引用的操作对象
     */
    public void setFormItem(String formItem) {
        this.formItem = formItem;
    }

    /**
     * 获取列的别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 获取列的别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取列上的操作函数
     */
    public String getFun() {
        return fun;
    }

    /**
     * 设置列上的操作函数
     */
    public void setFun(String fun) {
        this.fun = fun;
    }



    @Override
    public String getTargetName() {
        return StringUtils.isEmpty(getAlias()) ? getName() : getAlias();
    }

    /**
     * 函数
     */
    public SCol fun(String fun)
    {
        setFun(fun);
        return this;
    }

    /**
     * 列名
     */
    public SCol name(String name) {
        setName(name);
        return this;
    }

    /**
     * 别名
     */
    public SCol alias(String alias)
    {
        setAlias(alias);
        return this;
    }


    /**
     * 设置引用名称
     */
    public SCol formItem(String formItem)
    {
        setFormItem(formItem);
        return this;
    }


    @Override
    public void validate() throws SqlBuilderException {
        if (getStatement() == null);
          //  throw new SqlBuilderException("当前的 " + getName() + " 列不属于任何的语句.");

    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getColStatement(this);
    }

}
