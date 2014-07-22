package com.lemsun.formula;

import com.lemsun.core.formula.IFCol;
import org.apache.commons.lang3.StringUtils;

/**
 * 公式中定义的一个显示字段
 * User: 宗
 * Date: 13-4-21
 * Time: 下午6:20
 */
public class FCol implements IFCol {

    private String col;
    private String fun;
    private String ref;
    private String alias;

    public FCol() {

    }

    public FCol(String col) {
        this.col = col;
    }

    public String getRef() {
        if(StringUtils.isNotEmpty(ref)){
            return ref.toUpperCase();
        }
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        if(StringUtils.isNotEmpty(alias)){
            return alias;
        }
        else{
            return getCol();
        }
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取列所带的函数名称, 如果没有返回 null
     */
    @Override
    public String getFun() {
        return fun;
    }

    /**
     * 设置列所带的名称
     */
    public void setFun(String fun) {
        this.fun = fun;
    }

    /**
     * 设置列名
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     * 获取列名
     */
    @Override
    public String getCol() {
        return col;
    }

    @Override
    public String toString() {
        StringBuilder sql= new StringBuilder();
        if(StringUtils.isNotEmpty(this.getFun())) {
           boolean hasFunction = true;
            sql.append(",").append(hasFunction ? this.getFun()+"(" : "")
                    .append("[").append(this.getRef()).append(".").append(this.getCol()).append("]").append(hasFunction ? ")" : "").append(" AS ").append(this.getAlias());
        }
        else {
            sql.append(", [").append(this.getRef()).append(".").append(this.getCol()).append("] ").append(" AS ").append(this.getAlias());
        }
        return sql.toString();
    }
}
