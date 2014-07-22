package com.lemsun.formula;

import com.lemsun.core.formula.IFormulaJoin;
import com.lemsun.core.formula.IFormulaRef;
import com.lemsun.ldbc.ITableResource;
import org.apache.commons.lang3.StringUtils;

/**
 * User: 刘晓宝
 * Date: 14-3-11
 * Time: 下午4:05
 */
public class FormulaRef implements IFormulaRef {
    private String name;
    private String alias;
    private ITableResource  resource;
    private IFormulaJoin[] joins;
    private int joinState;


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ITableResource getResource() {
        return resource;
    }

    public void setResource(ITableResource resource) {
        this.resource = resource;
    }


    public IFormulaJoin[] getJoins() {
        return joins;
    }

    public void setJoins(FormulaJoin[] joins) {
        this.joins = joins;
    }

    public int getJoinState() {
        return joinState;
    }

    public void setJoinState(int joinState) {
        this.joinState = joinState;
    }

    @Override
    public String getAlias() {
        if (StringUtils.isNotEmpty(alias)){
            return alias.toUpperCase();
        }
        return getName().toUpperCase();

    }
}
