package com.lemsun.sql.builder;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询语句
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:46
 */
public class SSelect extends DefaultSQLSegment implements ISQLStatement, ISQLCol, ISQLValue, ISQLFormItem {


    private final ArrayList<ISQLCol> cols = new ArrayList<>();
    private ArrayList<SOrder> orders;
    private int index;
    private String alias;
    private SWhere where;
    private SForm form;
    private SGroupBy groupBy;
    private SPage page;
    private SSelectType selectType = SSelectType.ALL;
    private ArrayList<SParam> params;

    public SSelect() {
        this.setStatement(this);
    }

    /**
     *  获取查询列, 不能通过在 Cols 的返回集合中增加数据来增加显示列
     */
    public List<ISQLCol> getCols() {
        return new ArrayList<>(cols);
    }

    public List<SOrder> getOrders() {
        if(orders == null) return null;
        return new ArrayList<>(orders);
    }

    /**
     * 清除排序字段
     * @return
     */
    public SSelect clearOrders(){
        orders.clear();
        return this;
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

    public SForm getForm() {
        return form;
    }

    public void setForm(SForm form) {
        if(this.form == form) return;
        if(this.form != null) this.form.remove();
        if(form != null) addChild(form);
        this.form = form;
    }

    /**
     * 获取分页信息
     */
    public SPage getPage() {
        return page;
    }

    /**
     * 设置分页信息
     */
    public void setPage(SPage page) {
        if(this.page == page) return;
        if(this.page != null) this.page.remove();
        if(page != null) addChild(page);
        this.page = page;
    }


    /**
     * 获取分组信息
     */
    public SGroupBy getGroupBy() {
        return groupBy;
    }

    /**
     * 设置分组信息
     */
    public void setGroupBy(SGroupBy groupBy) {
        if(this.groupBy == groupBy) return;
        if(this.groupBy != null) this.groupBy.remove();
        if(groupBy != null) addChild(groupBy);

        this.groupBy = groupBy;
    }

    /**
     * 获取查询类型
     */
    public SSelectType getSelectType() {
        return selectType;
    }

    /**
     * 设置查询方式, 默认全部查询. 设置为 Distinct 为合并重复
     */
    public void setSelectType(SSelectType selectType) {
        this.selectType = selectType;
    }

    /**
     * 获取别名. 当 Select 语句用于一个子句中的时候就需要设置别名.
     */
    public String getAlias() {
        return alias;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public SJoin getJoin() {
        return null;
    }

    /**
     * 设置别名. 当 Select 语句用于一个子句中的时候就需要设置别名.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getTargetName() {
        return getAlias();
    }

    /**
     * 合并重复数据
     */
    public SSelect distinct()
    {
        selectType = SSelectType.Distinct;
        return this;
    }

    /**
     * 查询全部数据
     */
    public SSelect all() {
        selectType = SSelectType.Distinct;
        return this;
    }

    /**
     * 设置查询类型
     */
    public SSelect selectType(SSelectType type) {
        selectType = type;
        return this;
    }

    /**
     * 增加一个列显示
     */
    public SSelect col(SCol col) {
        return col((ISQLCol)col);
    }

    /**
     * 增加一个列值显示
     */
    public SSelect col(ISQLCol value) {
        addChild(value);
        cols.add(value);
        return this;
    }
    public SSelect col(List<ISQLCol> cols) {

         this.cols.addAll(cols);
        return this;
    }

    /**
     * 清空列
     * @return
     */
    public SSelect clearCols(){
        this.cols.clear();
        return this;
    }
    /**
     * 增加一个列值显示
     */
    public SSelect col(String col) {
        return col(new SCol(col));
    }

    /**
     * 增加一个列名显示并且在这个列名上有函数
     */
    public SSelect col(String col, String fun)
    {
        return col(new SCol(col).fun(fun));
    }

    /**
     * 设置查询对象
     */
    public SSelect form(SForm form) {
        setForm(form);
        return this;
    }

    /**
     * 设置查询对象
     */
    public SSelect form(ISQLFormItem item) {
        if(getForm() == null) setForm(new SForm());
        getForm().table(item);
        return this;
    }

    /**
     * 设置查询的第一张表格
     */
    public SSelect form(String table) {
        return form(new STable(table));
    }

    /**
     * 设置查询条件
     */
    public SSelect where(SWhere where) {
        setWhere(where);
        return this;
    }

    /**
     * 在当前的条件上追加一个表达式
     */
    public SSelect where(SExpre expre) {
        if(getWhere() == null) setWhere(new SWhere());
        getWhere().expr(expre);
        return this;
    }
    /**
     * 在当前的条件上追加一个表达式
     */
    public SSelect where(String col, SOperator op, Object value) {
        return where(SCondition.And, col, op, value);
    }
    /**
     * 在当前的条件上追加一个表达式
     */
    public SSelect where(SCondition condition, String col, SOperator op, Object value) {
        return where(new SExpre(new SCol(col), new SParam(value), op).condition(condition));
    }
    /**
     * 在当前的条件上追加一个表达式
     */
    public SSelect where(SCol col, SOperator op, SCol targetCol) {
        return where(SCondition.And, col, op, targetCol);
    }
    /**
     * 在当前的条件上追加一个表达式
     */
    public SSelect where(SCondition condition, SCol col, SOperator op, SCol targetCol) {
        return where(new SExpre().condition(condition).col(col).op(op).value(targetCol));
    }

    /**
     * 设置分组信息
     */
    public SSelect group(SGroupBy groupBy) {
        setGroupBy(groupBy);
        return this;
    }
    /**
     * 在分组信息中追加一个分组列
     */
    public SSelect group(SCol col) {
        if(getGroupBy() == null) setGroupBy(new SGroupBy());
        getGroupBy().col(col);
        return this;
    }
    /**
     * 在分组信息中追加一个分组列
     */
    public SSelect group(String col) {
        return group(new SCol(col));
    }

    /**
     * 追加一个排序集合
     */
    public SSelect order(List<SOrder> orders) {
        this.orders.addAll(orders);
        return this;
    }
    /**
     * 追加一个排序列
     */
    public SSelect order(SOrder order) {
        if(this.orders == null) {
            this.orders = new ArrayList<>();
        }

        addChild(order);
        orders.add(order);
        return this;
    }
    /**
     * 追加一个排序列
     */
    public SSelect order(String col, SOrderType orderType) {
        return order(new SOrder().col(col).type(orderType));
    }
    /**
     * 追加一个排序列
     */
    public SSelect order(String col) {
        return order(col, SOrderType.Asc);
    }

    /**
     * 设置分页信息
     */
    public SSelect page(SPage page) {
        setPage(page);
        return this;
    }

    public SSelect page(int start, int limit) {
        return page(new SPage(start, limit));
    }

    /**
     * 给当前的语句设置别名
     */
    public SSelect alias(String alias) {
        setAlias(alias);
        return this;
    }

    @Override
    public int nextIndex() {
        return index++;
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
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;

        if(item instanceof ISQLCol) {
            cols.remove(item);
        }
        else if(item instanceof SOrder) {
            orders.remove(item);
        }
        else if(item == form) {
            form = null;
        }
        else if(item == where) {
            where = null;
        }
        else if(item == groupBy) {
            groupBy = null;
        }
        else if(item == page) {
            page = null;
        }
        return true;
    }


    @Override
    public void validate() throws SqlBuilderException {
        if (cols.size() == 0)
            throw new SqlBuilderException("当前构造的语句没有列信息.");

        for(ISQLCol col : cols)
        {
            col.validate();
        }

        if (getForm() == null)
            throw new SqlBuilderException("查询语句必须具备 From 查询对象");

        getForm().validate();

        if (getWhere() != null) getWhere().validate();

        if (getPage() != null) getPage().validate();

        if (orders != null && orders.size() > 0)
        {
            for (SOrder o : orders)
            {
                o.validate();
            }
        }

        if (getGroupBy() != null) getGroupBy().validate();
    }


    public String toSQL(ISQLAdapter adapter, boolean withpage) throws SqlBuilderException {
        return adapter.getSelectStatement(this, withpage);
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        if(getParent() == null) validate();
        index = -1;
        params =  null;
        return adapter.getSelectStatement(this, true);
    }
}
