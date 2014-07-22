package com.lemsun.websocket.commands;

import com.lemsun.core.ArrayData;
import com.lemsun.formula.Formula;
import com.lemsun.formula.service.IFormulaService;
import com.lemsun.ldbc.TableData;
import com.lemsun.websocket.AbstractSocketCommand;
import com.lemsun.websocket.commands.models.FormulaDataGetModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 执行公式数据获取命令
 * User: 宗旭东
 * Date: 13-12-6
 * Time: 上午9:58
 */
public class FormulaDataGetCommand  extends AbstractSocketCommand {

    private IFormulaService formulaService;

    @Autowired
    public FormulaDataGetCommand(IFormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @Override
    public void excute() throws Exception {

        FormulaDataGetModel formula = getJsonParam(FormulaDataGetModel.class);

        ArrayData data = formulaService.getArrayData(formula.getFormula(), formula.getQuery());

        ReFormulaDataGetCommand reCommand = new ReFormulaDataGetCommand(getId(), data);

        getResponseSenter().sent(reCommand);

    }
}
