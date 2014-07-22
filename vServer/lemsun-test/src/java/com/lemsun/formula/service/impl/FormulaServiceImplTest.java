package com.lemsun.formula.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.FCondition;
import com.lemsun.core.formula.FOperater;
import com.lemsun.core.formula.FormulaException;
import com.lemsun.core.formula.IFormula;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.formula.*;
import com.lemsun.formula.service.IFormulaService;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.TableData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 宗
 * Date: 13-4-22
 * Time: 下午3:09
 */

public class FormulaServiceImplTest extends TestSupport {

    @Autowired
    private IFormulaService service;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;

    @Test
    public void testParse() throws FormulaException {

    }

    @Test
    public void testFormula() throws Exception {

        Formula formula = new Formula();

        FormulaRef[] ref = new FormulaRef[1];
        ref[0] = new FormulaRef();
        ref[0].setName("C000006115");

        formula.setRefs(ref);


        FColRange range = new FColRange();

        ArrayList<FCol> cols = new ArrayList<>();
        cols.add(new FCol("Code"));
        cols.add(new FCol("D"));
        cols.add(new FCol("E"));
        cols.add(new FCol("F"));
        cols.add(new FCol("G"));
        range.setCols(cols);

        formula.setColRange(range);


        FormulaQuery query = new FormulaQuery();
        //query.setAdate("20140312");
        query.setDataCount(1);
        query.setLimit(200);
        query.setStart(0);

        ArrayData data = service.getArrayData(formula, query);


        getLog().debug( "RowCount : " + data.getData().size());

    }



    /**
     * 测试公式执行
     *
     * @throws FormulaException
     */
    @Test
    public void testExcute() throws FormulaException, IOException {

        String formula;
        ArrayData data;

        String json="{\"input\":\"C000004476!Code,d,e(d=@a)\",\"ref\":\"C000004476\",\"colRange\":{\"cols\":{\"col\":\"Code\"},{\"col\":\"d\"},{\"col\":\"e\"}],\"range\":false},\"expression\":{\"var\":\"d\",\"value\":\u007F\"fg\"},\"formulaValue\":{\"input\":\"FORMULA\",\"ref\":\"a\"},\"condition\":-1,\"operater\":1}}";

        //Formula f = jsonObjectMapper.readValue(json, Formula.class);

        FColRange fColRange=new FColRange();

        ArrayList<FCol> cols = new ArrayList<>();
        FCol col=new FCol();
        col.setCol("G");
        col.setAlias("G");
        col.setRef("A");
        cols.add(col);
        FCol col2=new FCol();
        col2.setCol("F");
        col2.setAlias("F");
        col2.setRef("A");
        cols.add(col2);
        fColRange.setCols(cols);
        FormulaRef[] refs= new FormulaRef[1];
        FormulaRef ref= new FormulaRef();
        ref.setName("C000000095");
        ref.setAlias("A");
        refs[0]=ref;
        FExpression expression=new FExpression();
        expression.setVar("F");
        expression.setValue("1");
        expression.setRef("A");
        expression.setCondition(FCondition.And);
        expression.setOperater(FOperater.Eq);

        FExpression expression2=new FExpression();
        expression2.setVar("G");
        expression2.setValue("1");
        expression.setRef("A");
        expression2.setCondition(FCondition.And);
        expression2.setOperater(FOperater.Eq);

        expression.setNextExpression(expression2);

        Formula f2=new Formula();
        f2.setExpression(expression);
        f2.setColRange(fColRange);
        f2.setRefs(refs);
        data = service.getArrayData(f2);
        getLog().debug("开始"+data.toString());
    }

}