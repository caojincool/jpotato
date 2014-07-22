package com.lemsun.data.sqlserver.service.support;

import com.lemsun.core.formula.IFCol;

import com.lemsun.data.lmstable.Column;
import com.lemsun.ldbc.ITableResource;

/**
     * 包装选择语句的临时类
     */
   public class SelectCol implements ISelectCol {
        private Column col;
        private IFCol formulaCol;
        private boolean  flag=true; //是否后台自动添加的

        private ITableResource tableResource;
        SelectCol(ITableResource tableResource,Column col, IFCol formulaCol) {
            this.col = col;
            this.formulaCol = formulaCol;
            this.tableResource=tableResource;
        }

        public ITableResource getTableResource() {
            return tableResource;
        }

        public void setTableResource(ITableResource tableResource) {
            this.tableResource = tableResource;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
             this.flag = flag;
        }

        public Column getCol() {
            return col;
        }

        public IFCol getFormulaCol() {
            return formulaCol;
        }
    }