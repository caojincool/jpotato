package com.lemsun.web.manager.controller.lkg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 4代程序导入
 * User: 刘晓宝
 * Date: 13-9-18
 * Time: 上午9:56
 */
@Controller
@RequestMapping(value = "package/fourProgram")
public class FourProgramController {
    /**
     * 显示用户统计视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("lkg/fourProgram/view");
        request.getSession().setAttribute("head",2);
        request.getSession().setAttribute("left",2);
        return view;
    }
}
