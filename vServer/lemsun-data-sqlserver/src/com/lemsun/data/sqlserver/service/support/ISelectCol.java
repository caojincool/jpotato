package com.lemsun.data.sqlserver.service.support;

import com.lemsun.core.formula.IFCol;
import com.lemsun.data.lmstable.Column;
import com.lemsun.ldbc.ITableResource;

/**
 * User: 刘晓宝
 * Date: 14-2-27
 * Time: 上午9:07
 */
public interface ISelectCol {
    /**
     * 列所在表
     * @return
     */
   public ITableResource getTableResource();
    /**
     * 物理列
     * @return
     */
    public Column getCol();
    /**
     * 公式列
     * @return
     */
    public IFCol getFormulaCol();

    /**
     * 是否后台处理列
     * @return
     */
    public boolean isFlag();
}
