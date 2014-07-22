package com.lemsun.sql.builder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:00
 */
public class DefaultSQLAdapter implements ISQLAdapter {

    private final static ISQLAdapter instance = new DefaultSQLAdapter();

    /**
     * 获取默认的SQL适配器
     */
    public static ISQLAdapter getInstance() {
        return instance;
    }

    /**
     * 输出SQL语句, 并根据参数是否需要输出 page 信息
     * @param withpage
     * @return
     * @throws SqlBuilderException
     */
    @Override
    public String getSelectStatement(SSelect select, boolean withpage) throws SqlBuilderException {

        if(withpage && select.getParent() == null && select.getPage() != null && select.getPage().isPage()) {
            return select.getPage().toSQL(this);
        }
        else {
            StringBuilder sql = new StringBuilder();

            if(select.getParent() != null) {
                sql.append("(\n");
            }

            boolean isFirst = true;

            sql.append("SELECT ");

            if(select.getSelectType() == SSelectType.Distinct) {
                sql.append("DISTINCT ");
            }

            sql.append('\n');

            for(ISQLCol col : select.getCols()) {
                if(isFirst) {
                    isFirst = false;
                }
                else {
                    sql.append(", ");
                }

                sql.append(col.toSQL(this));
            }

            if(select.getForm() != null) {
                sql.append("\nFROM ").append(select.getForm().toSQL(this));
            }

            if(select.getWhere() != null) {
                sql.append("\nWHERE ").append(select.getWhere().toSQL(this));
            }

            if(select.getGroupBy() != null) {
                sql.append("\nGROUP BY ").append(select.getGroupBy().toSQL(this));
            }
            List<SOrder> orders = select.getOrders();

            isFirst = true;
            if(select.getParent() == null && orders != null && orders.size() > 0) {
                sql.append("\nORDER BY ");
                for(SOrder o : orders) {
                    if(isFirst) isFirst = false; else sql.append(", ");
                    sql.append(o.toSQL(this));
                }
            }

            if(select.getParent() != null) {
                sql.append(")\n");
                if(StringUtils.isNotEmpty(select.getTargetName())) {
                    sql.append(" AS ").append(select.getTargetName());
                }
            }
            return sql.toString();
        }

    }

    @Override
    public String getColStatement(SCol col) throws SqlBuilderException {
        StringBuilder sql = new StringBuilder();

        if (StringUtils.isNotEmpty(col.getFun()))
        {
            sql.append(col.getFun()).append('(');
        }

        if (StringUtils.isNotEmpty(col.getFormItem()))
        {
            sql.append(col.getFormItem()).append('.');
        }
        sql.append(col.getName());

        if (StringUtils.isNotEmpty(col.getFun()))
        {
            sql.append(')');
        }

        if (StringUtils.isNotEmpty(col.getAlias()))
        {
            sql.append(" AS ").append(col.getAlias());
        }
        return sql.toString();
    }

    @Override
    public String getFormStatement(SForm form) throws SqlBuilderException {
        String sql = "";

        for(ISQLFormItem item : form.getTables()) {
            sql += item.toSQL(this) + " ";
        }

        return sql;
    }

    @Override
    public String getGroupByStatement(SGroupBy groupBy) throws SqlBuilderException {

        List<SCol> cols = groupBy.getCols();

        StringBuilder sql = new StringBuilder("\n ");

        boolean isFirst = true;

        for(SCol c : cols) {
            if(isFirst) isFirst = false; else sql.append(", ");
            sql.append(c.toSQL(this));
        }

        SWhere having = groupBy.getHaving();

        if(having != null) sql.append(having.toSQL(this));

        return sql.toString();
    }

    @Override
    public String getJoinStatement(SJoin join) throws SqlBuilderException {

        String sql = join.getFormItem().toSQL(this, false);

        SJoinType s = join.getJoinType();

        if(s == SJoinType.Join) {
            sql = " JOIN " + sql;
        }
        else if(s == SJoinType.JoinAll) {
            sql = " FULL JOIN  " + sql;
        }
        else if(s == SJoinType.Left) {
            sql = " LEFT JOIN " + sql;
        }
        else if(s == SJoinType.Right) {
            sql = " RIGHT JOIN " + sql;
        }

        if (join.getOn() != null)
            sql += " ON " + join.getOn().toSQL(this);

        return sql;
    }

    @Override
    public String getOrderStatement(SOrder order) throws SqlBuilderException {
        return order.getCol().toSQL(this) + (order.getType() == SOrderType.Desc ? " DESC" : "");
    }

    @Override
    public String getPageStatement(SPage page) throws SqlBuilderException {



        SSelect select = page.getSelect();


        String selSql = page.getSelect().toSQL(this, false);

        StringBuilder sql = new StringBuilder("SELECT ");

        if(page.getLimit() != null) {
            sql.append("TOP ").append(page.getLimit().toSQL(this)).append(" ");
        }

        List<ISQLCol> cols = select.getCols();
        boolean isfirst = true;

        for(ISQLCol col : cols) {
            if(isfirst) isfirst = false; else sql.append(", ");
            sql.append(col.getTargetName());
        }

        sql.append(" FROM (\nSELECT  *, ROW_NUMBER() OVER (ORDER BY ");

        List<SOrder> orders = select.getOrders();
        isfirst = true;
        if(orders != null)
        for(SOrder o : orders) {
            if(isfirst) isfirst = false; else sql.append(", ");
            sql.append(o.toSQL(this));
        }

        sql.append(") AS Row_Number FROM ( \n")
                .append(selSql)
                .append("\n) AS __page_table \n) AS __page_final ");

        if(page.getStart() != null) {
            sql.append("\nWHERE Row_Number >= ").append(page.getStart().toSQL(this));
        }

        return sql.toString();
    }

    @Override
    public String getParamStatement(SParam param) throws SqlBuilderException {

        String sql;

        if(param.isParameter()) {
            param.setIndex(param.getStatement().nextIndex());
            sql = getParamSymbol();
        }
        else {
            sql = getSQLValue(param.getValue());
        }

        if(StringUtils.isNotEmpty(param.getAlias())) {
            sql = "(" + sql + ") AS " + param.getAlias();
        }

        return sql;
    }

    @Override
    public String getParamArrayStatement(SParamArray paramArray) throws SqlBuilderException {

        StringBuilder sql = new StringBuilder("(");

        boolean isStart = true;
        for(ISQLValue v : paramArray.getValues())
        {
            if(isStart)
            {
                isStart = false;
            }
            else {
                sql.append(", ");
            }

            sql.append(v.toSQL(this));
        }

        sql.append(')');

        return sql.toString();
    }

    @Override
    public String getTableStatement(STable table, boolean withjoin) throws SqlBuilderException {

        if (withjoin && table.getJoin() != null)
        {
            return table.getJoin().toSQL(this);
        }

        String sql = table.getName();
        if (StringUtils.isNotEmpty(table.getAlias()))
        {
            sql += " AS " + table.getAlias();
        }
        return sql;
    }


    @Override
    public String getWhereStatement(SWhere where) throws SqlBuilderException {

        List<SExpre> expres = where.getExpres();
        boolean isfirst = true;
        String sql = "";
        for(SExpre expre : expres) {
            if(isfirst) isfirst = false; else sql += " ";
            sql += expre.toSQL(this);
        }
        return sql;
    }

    @Override
    public String getExpreStatement(SExpre expre) throws SqlBuilderException {
        return getCondition(expre.getCondition())
                + " " + expre.getCol().toSQL(this)
                + " " + getOperator(expre.getOperator())
                + " " + expre.getValue().toSQL(this);
    }


    public String getOperator(SOperator op) throws SqlBuilderException {
        if (op == SOperator.Eq) {
            return "=";
        } else if (op == SOperator.GEq) {
            return ">=";
        } else if (op == SOperator.Greater) {
            return ">";
        } else if (op == SOperator.In) {
            return "in";
        } else if (op == SOperator.LEq) {
            return "<=";
        } else if (op == SOperator.Less) {
            return "<";
        } else if (op == SOperator.Like) {
            return "like";
        } else if (op == SOperator.UnEq) {
            return "<>";
        } else if (op == SOperator.UnIn) {
            return "not in";
        } else if (op == SOperator.UnLike) {
            return "not like";
        }

        throw new SqlBuilderException("不支持的操作符");

    }

    public String getCondition(SCondition condition) throws SqlBuilderException {
        if (SCondition.And == condition)
            return "AND";
        else if (SCondition.Not == condition)
            return "NOT";
        else if (SCondition.Or == condition)
            return "OR";
        else if (SCondition.Start == condition)
            return "";

        throw new SqlBuilderException("不支持的操作符");

    }

    public String getParamSymbol() {
        return "?";
    }

    public String getNULL() {
        return "NULL";
    }

    public String getSQLValue(Object value) {
        if (value == null) {
            return getNULL();
        } else if (value instanceof String || value instanceof UUID) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }



}
