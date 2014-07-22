package com.lemsun.client.formula;

import com.lemsun.client.core.formula.IFCol;

/**
 * User: 宗旭东
 * Date: 13-10-15
 * Time: 下午2:34
 */
public class FCol implements IFCol {

    private String col;
    private String fun;
    private String ref;
    private String alias;

    public FCol(String col, String ref, String fun) {
        this.col = col;
        this.fun = fun;
        this.ref = ref;
    }

    public FCol(String col) {
        this.col = col;
    }


    public FCol() {

    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
