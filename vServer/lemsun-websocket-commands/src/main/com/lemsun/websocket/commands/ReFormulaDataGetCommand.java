package com.lemsun.websocket.commands;

import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.FormulaException;
import com.lemsun.ldbc.TableData;
import com.lemsun.websocket.AbstractSocketResponseCommand;

import java.io.IOException;

/**
 * 将公式执行的结果进行返回
 * User: 宗旭东
 * Date: 13-12-6
 * Time: 上午10:02
 */
public class ReFormulaDataGetCommand extends AbstractSocketResponseCommand {

    public static final String Command = "re.formula.data";

    private ArrayData data;

    public ReFormulaDataGetCommand(String targetId, ArrayData data) {
        super(targetId, Command);
        this.data = data;
    }

    @Override
    public String executeContext() {
        try {
            return toJson(data);
        } catch (IOException e) {
            throw new FormulaException(e.getMessage(), "0001");
        }
    }
}
