package com.lemsun.websocket.commands.models;

import com.lemsun.formula.Formula;
import com.lemsun.ldbc.FormulaQuery;

/**
 * User: 宗旭东
 * Date: 14-3-12
 * Time: 上午11:59
 */
public class FormulaDataGetModel {

    private Formula formula;
    private FormulaQuery query;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public FormulaQuery getQuery() {
        return query;
    }

    public void setQuery(FormulaQuery query) {
        this.query = query;
    }
}
