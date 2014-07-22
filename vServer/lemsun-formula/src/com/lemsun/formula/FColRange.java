package com.lemsun.formula;

import com.lemsun.core.formula.FormulaException;
import com.lemsun.core.formula.IFCol;
import com.lemsun.core.formula.IFColRange;
import com.lemsun.data.lmstable.Table5Resource;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公式中定义显示区的列表
 * User: 宗
 * Date: 13-4-21
 * Time: 下午6:21
 */
public class FColRange implements IFColRange {

    private ArrayList<FCol> cols = new ArrayList<>();
    private boolean range;


    public FColRange(Table5Resource resource, FCol col) throws FormulaException {

        if (col == null) {
            throw new FormulaException("不能输入空的列组", "001");
        }
    }


    public FColRange(Table5Resource resource, FCol[] cols) throws FormulaException {
        if (ArrayUtils.isEmpty(cols)) {
            throw new FormulaException("不能输入空的列组", "001");
        }

        for (FCol c : cols) {
            this.cols.add(c);
        }
    }


    public FColRange(Table5Resource resource, FCol fromCol, FCol toCol) {
    }

    public FColRange() {

    }

    /**
     * 获取全部的列
     */
    @Override
    public IFCol[] getAll() {
        return cols.toArray(new FCol[cols.size()]);
    }

    /**
     * 设置传递的列
     */
    public void setCols(List<FCol> cols) {
        this.cols.addAll(cols);
    }

    /**
     * 获取当前的列是否是一个区域
     */
    @Override
    public boolean isRange() {
        return range;
    }

    /**
     * 设置当前的列是否是一个区域
     */
    public void setRange(boolean range) {
        this.range = range;
    }
}
