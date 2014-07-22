package com.lemsun.client.formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.client.core.formula.IFCol;
import com.lemsun.client.core.formula.IFColRange;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 公式中引用列的集合
 *
 * User: 宗旭东
 * Date: 13-4-24
 * Time: 下午2:01
 */
public class ColRange implements IFColRange {

    private ArrayList<FCol> cols = new ArrayList<>();
    private boolean isRange = false;
    private Formula formula;


    public ColRange(Collection<FCol> cols) {
        this.cols.addAll(cols);
        isRange = false;
    }

    public ColRange(FCol col, FCol to) {
        cols.add(col);
        cols.add(to);
        isRange = true;
    }

    /**
     * 返回当前的公式的列集合
     */
    public FCol[] getCols() {
        return cols.toArray(new FCol[cols.size()]);
    }


    @Override
    @JsonIgnore
    public IFCol[] getAll() {
        return null;
    }

    public boolean isRange() {
        return isRange;
    }

    @JsonIgnore
    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
}
