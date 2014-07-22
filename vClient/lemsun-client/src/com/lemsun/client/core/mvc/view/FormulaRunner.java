package com.lemsun.client.core.mvc.view;

import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.formula.FormulaException;
import com.lemsun.client.core.service.IFormulaService;

import java.util.Map;

/**
 * 公式执行代理
 * User: 宗旭东
 * Date: 13-4-26
 * Time: 上午11:29
 */
public class FormulaRunner {

    private Map scope;
    private IFormulaService formulaService;

    public FormulaRunner(Map scope, IFormulaService formulaService) {
        this.scope = scope;
        this.formulaService = formulaService;
    }

    public ArrayData run(String formula) throws FormulaException {
        return formulaService.getFormulaData(scope, formula);
    }

}
