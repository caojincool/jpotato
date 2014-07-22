package com.lemsun.web.manager.controller.iservice;


import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.FormulaException;
import com.lemsun.core.formula.IFormula;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.formula.Formula;
import com.lemsun.formula.service.IFormulaService;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.TableData;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

/**
 * 对外公式处理接口
 * User: 宗旭东
 * Date: 13-4-25
 * Time: 上午9:47
 */
@Controller("interfaceFormulaController")
@RequestMapping("/interface/{plateform}/formula")
public class FormulaController {

    @Autowired
    private IFormulaService formulaService;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;


    /**
     * 使用formula数据对象获取公式数据
     *
     * C00000101!A,B,SUM(C)(B=100&C=100)
     *
     * ID COLS EXPRESS
     * COLS: [A,B, SUM(C)]
     * EXPRESS: [ B = 100, C = 100 ]
     *
     * {
     *  ID:C00000101,
     *  COLS: [A,B, SUM(C)],
     *  EXPRESS: [ B = 100, C = 100 ]
     * }
     *
     *
     * @param request
     * @param formulaStr
     * @return
     */
    @RequestMapping(value = "data/get", method = RequestMethod.POST)
    public ResponseEntity<ArrayData> getData(HttpServletRequest request, String formulaStr) throws Exception {

        String json = request.getParameter("formula");
        String queryStr = request.getParameter("query");

        Formula formula = jsonObjectMapper.readValue(json, Formula.class);

        FormulaQuery query= jsonObjectMapper.readValue(queryStr, FormulaQuery.class);

        ArrayData data = formulaService.getArrayData(formula);

        return new ResponseEntity<>(data);

    }


    /**
     * 对有一个公式进行赋值计算
     * @param request 请求对象
     * @return 返回执行成功
     * @throws FormulaException 抛出公式执行异常
     */
    @RequestMapping("data/assign")
    public ResponseEntity<String> setData(HttpServletRequest request) throws Exception {

        String formulaStr = request.getParameter("formula");
        String data = request.getParameter("data");
        String dataType = request.getParameter("datatype");

        IFormula formula = jsonObjectMapper.readValue(formulaStr, Formula.class);

        formulaService.setFormulaData(formula, dataType, data);

        return new ResponseEntity<>("OK");

    }


    /**
     * 输出设计器页面
     * @return
     */
    @RequestMapping("desginer")
    public ModelAndView desginerView(String formula) {

        ModelAndView view = new ModelAndView("iservice/formula/desginer");

        view.addObject("formula", formula);


        return view;
    }
    @RequestMapping("desginer_proxy")
    public ModelAndView desginerViewProxy(String formula) {
        ModelAndView view = new ModelAndView("iservice/formula/desginer_proxy");
        return view;
    }


}
