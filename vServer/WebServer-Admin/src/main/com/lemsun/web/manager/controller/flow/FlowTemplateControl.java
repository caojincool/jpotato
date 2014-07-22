package com.lemsun.web.manager.controller.flow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * User: 刘晓宝
 * Date: 13-9-10
 * Time: 下午5:20
 */
@Controller
@RequestMapping("flow/template")
public class FlowTemplateControl {
    /**
     * 显示流程模板视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("flow/template/view");
        request.getSession().setAttribute("head",3);
        request.getSession().setAttribute("left",1);
        return view;
    }
}
