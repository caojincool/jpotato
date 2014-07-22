package com.lemsun.data.sqlserver;

import com.lemsun.sql.builder.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 为SQL Server 构造的 SQL 语句
 */
public class SqlServerSQLAdapter extends DefaultSQLAdapter {


    @Override
    public String getPageStatement(SPage page) throws SqlBuilderException {

        SSelect select = page.getSelect();


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


        sql.append(" FROM (\n");


        boolean isFirst = true;

        sql.append("SELECT ");

        if(select.getSelectType() == SSelectType.Distinct) {
            sql.append("DISTINCT ");
        }

        sql.append('\n');
        sql.append("  ROW_NUMBER() OVER ( ORDER BY ");
        List<SOrder> orders = select.getOrders();
        isfirst = true;
        if(orders != null)
            for(SOrder o : orders) {
                if(isfirst) isfirst = false;
                else sql.append(", ");
                sql.append(o.toSQL(this));
            }
        sql.append(") AS Row_Number,\n");
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

        sql.append("\n) AS __page_table");

        sql.append("\nWHERE Row_Number >= ").append(page.getStart().toSQL(this));


        return sql.toString();

    }
}
