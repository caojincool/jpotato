package com.lemsun.web.manager.controller.flow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: 刘晓宝
 * Date: 13-9-10
 * Time: 下午5:21
 */
@Controller
@RequestMapping("flow/instance")
public class FlowInstanceControl {
    /**
     * 显示流程实例视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("flow/instance/view");


        request.getSession().setAttribute("head",3);
        request.getSession().setAttribute("left",2);
        return view;
    }
}
