package com.lemsun.client.core.formula;

import com.lemsun.client.core.LemsunException;

/**
 * 公式解析执行异常
 * User: 宗
 * Date: 13-4-22
 * Time: 下午1:55
 */
public class FormulaException extends LemsunException {


    /**
     * 公式执行异常
     */
    public final static String ExecuteCode = "001";

    /**
     * 格式异常
     */
    public final static String FormatCode = "002";


    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     */
    public FormulaException(String msg) {
        super(msg);
    }
}
