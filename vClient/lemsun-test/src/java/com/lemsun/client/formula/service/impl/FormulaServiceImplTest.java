package com.lemsun.client.formula.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.formula.IFormula;
import com.lemsun.client.formula.Formula;
import com.lemsun.client.core.formula.FormulaException;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.mvc.view.FormulaRunner;
import com.lemsun.client.core.service.IFormulaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: 宗旭东
 * Date: 13-4-25
 * Time: 下午3:33
 */
public class FormulaServiceImplTest extends TestSupport {

    @Autowired
    private IFormulaService formulaService;

    private FormulaRunner formulaRunner;

    @Autowired
    private JsonObjectMapper objectMapper;

    @Test
    public void testFormulaData() throws FormulaException {
        ArrayData data;
        data = formulaService.getFormulaData(null, "C000001853!Code,H,J,K(D=5&F=3&L=true)");

        getLog().debug(data.toString());

//        data = formulaService.getFormulaData(null, "C000000969!A(01)");
//
//        getLog().debug(data.toString());

    }

    @Test
    public void testFormula()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("cid",2);
        map.put("a","hi");
        map.put("b","hi");
        ArrayData data;

        formulaRunner=new FormulaRunner(map,formulaService);
        data=formulaRunner.run("C000004476!Code,d,e"); // 通过C000003223!(F,GJK)(E=05) //不通过
        Object obj=data.getValue();
        for(int i=0;i<data.getRowCount();i++){
            getLog().debug(data.getValue(i,"e").toString());
        }
    }

    /**
     * 测试公式书写解析格式
     * @throws IOException
     */
    @Test
    public void testFormulaStr() throws IOException {

        String formula = null;
        IFormula f1;


        formula = "C000005734<=>C000005757[C000005734.Code =C000005757.Code]!Code,C000005734.E,C000005757.Code,C000005757.D(C000005757.D in [100, @C0001!A,B(B=200)])(+Code)";
        f1 = formulaService.parse(formula);

        getLog().debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(f1));
//
//        formula = "C000003223!F,G,sum(J,K)(C = @CC!*)";
//        f1 = formulaService.parse(formula);
//
//        getLog().debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(f1));
//
//
//        formula = "C000003223!F,G,sum(J,K)(C = @CC!*) = C000003223!F,G,sum(J,K)(E=05 & E=04)";

    }


    @Test
    public void testArray(){
        ArrayData data=new ArrayData("sdf");
        getLog().debug(data.toString());
    }
}
