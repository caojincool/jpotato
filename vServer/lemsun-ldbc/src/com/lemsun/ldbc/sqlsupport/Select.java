package com.lemsun.ldbc.sqlsupport;

import java.util.List;

/**
 * Created by xudong on 14-2-22.
 */
public class Select {

    private List<Column> columns;
    private List<Table> tables;
    private Where where;
    private Order order;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    public String toSQL() {

        StringBuilder sql = new StringBuilder("SELECT ");

        for(Column c : getColumns()) {
            sql.append(c.ToSQL());
        }


        return sql.toString();


    }

}
