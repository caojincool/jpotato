package com.lemsun.data.sqlserver.service.support;

import com.lemsun.sql.builder.SParam;

/**
 * Created by xudong on 14-3-3.
 */
public class FormulaParam extends SParam {


    public FormulaParam(WhereParam param) {
        this.param = param;
    }

    private WhereParam param;

    public WhereParam getParam() {
        return param;
    }

    public void setParam(WhereParam param) {
        this.param = param;
    }

}
