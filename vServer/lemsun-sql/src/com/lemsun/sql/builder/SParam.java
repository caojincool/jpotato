package com.lemsun.sql.builder;

/**
 * SQL 语句中的参数对象
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午4:10
 */
public class SParam extends DefaultSQLSegment implements ISQLValue, ISQLCol {
    /**
     * 传参常量
     */
    public final static Object Parameter = new Object();


    /**
     * 构造一个常量参数.
     */
    public SParam(Object value) {
        this.value = value;
    }



    /**
     * 构造一个需要传参的 参数
     */
    public SParam() {
    }

    private int index;
    private String name;
    protected Object value;
    private String alias;


    /**
     * 设置获取参数在语句中的位置, 从 0 开始. <b>注意, 只有在语句生成后参数才有位置信息</b>
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置获取参数在语句中的位置, 从 0 开始. <b>注意, 只有在语句生成后参数才有位置信息</b>
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 设置获取参数名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置获取参数名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置获取传入的参数
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置获取传入的参数
     */
    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getTargetName() {
        return getAlias();
    }

    /**
     * 判断当前的参数是否是一个传参类型
     */
    public boolean isParameter()
    {
        return getValue() == Parameter;
    }


    public SParam alias(String alias) {
        setAlias(alias);
        return this;
    }

    public SParam value(Object value) {
        setValue(value);
        return this;
    }


    @Override
    public void validate() throws SqlBuilderException {
        if(value != null) return;
        throw new SqlBuilderException("数组参数的数值不能为空");
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getParamStatement(this);
    }

    @Override
    public String toString() {
        try {
            return "Parameter : " + getParent().toSQL() + "; Index= " + index + " Name=" + name;
        } catch (SqlBuilderException e) {
            return "ERROR";
        }
    }

}
