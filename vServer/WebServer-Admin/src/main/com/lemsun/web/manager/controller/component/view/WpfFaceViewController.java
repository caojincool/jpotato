package com.lemsun.web.manager.controller.component.view;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Wpf 的资源显示控制
 * User: Xudong
 * Date: 13-1-25
 * Time: 下午3:27
 */
@Controller
@RequestMapping(value = "component")
public class WpfFaceViewController {

    @Autowired
    private IResourceService resourceService;

    /**
     * wpf组件
     * 表组组件 预览
     *
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping(value = {"wpfskin/view/{pid}", "tabelgp5/view/{pid}"})
    public ModelAndView wpfskinView(HttpServletRequest request, @PathVariable String pid) {
        ModelAndView view = new ModelAndView("component/view/WPFSKIN");

        LemsunResource resource = resourceService.getBaseResource(pid.toUpperCase());

        view.addObject("resource", resource);

        return view;
    }

    /**
     * 显示4代数据表维护
     */
    @RequestMapping(value = "view/{pid}")
    public ModelAndView view(HttpServletRequest request, @PathVariable("pid") String pid) {
        ModelAndView view = new ModelAndView("component/view/WPFSKIN");

        LemsunResource resource = resourceService.getBaseResource(pid.toUpperCase());

        view.addObject("resource", resource);

        return view;
    }
}
