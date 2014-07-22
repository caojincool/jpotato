package com.lemsun.data.sqlserver.service.support;

import com.lemsun.core.formula.IFExpression;
import com.lemsun.data.lmstable.Column;
import com.lemsun.sql.builder.SParam;

/**
     * 包装条件语句的临时类
     */
  public  class WhereParam extends SParam {
        private Column col;

        public WhereParam(Column col,  Object value) {
            this.col = col;
            super.value = value;
        }


    public Column getCol() {
            return col;
        }



    public Object getValue()
        {
            return col.convert(value);
        }

    public int getSqlType()
        {
            return col.getSQLType();
        }
    }