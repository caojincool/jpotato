package com.lemsun.web.manager.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: 刘晓宝
 * Date: 13-9-10
 * Time: 下午5:23
 */
@Controller
@RequestMapping("report")
public class ReportControl {
    /**
     * 显示用户统计视图
     */
    @RequestMapping("user/view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("report/user/view");
        request.getSession().setAttribute("head",6);
        request.getSession().setAttribute("left",1);
        return view;
    }
    /**
     * 显示组件统计视图
     */
    @RequestMapping("component/view")
    public ModelAndView component(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("report/component/view");
        request.getSession().setAttribute("head",6);
        request.getSession().setAttribute("left",2);
        return view;
    }
    /**
     * 显示流程统计视图
     */
    @RequestMapping("flow/view")
    public ModelAndView flowView(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("report/flow/view");
        request.getSession().setAttribute("head",6);
        request.getSession().setAttribute("left",3);
        return view;
    }
    /**
     * 显示任务统计视图
     */
    @RequestMapping("task/view")
    public ModelAndView taskView(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("report/task/view");
        request.getSession().setAttribute("head",6);
        request.getSession().setAttribute("left",4);
        return view;
    }
    /**
     * 显示系统实例统计视图
     */
    @RequestMapping("system/view")
    public ModelAndView systemView(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("report/system/view");

        request.getSession().setAttribute("head",6);
        request.getSession().setAttribute("left",5);
        return view;
    }
}
