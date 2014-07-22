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
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-12
 * Time: 上午9:54
 * 4 代数据表打开查看
 */
@Controller
@RequestMapping(value = {"component/tabelgp4", "component/TABELGP4"})
public class TableGroup4ViewController {

    @Autowired
    private IResourceService resourceService;

    /**
     * 显示4代数据表维护
     */
    @RequestMapping(value = "view/{pid}")
    public ModelAndView view(HttpServletRequest request, @PathVariable("pid") String pid) {
        ModelAndView view = new ModelAndView("component/view/TABELGP4");

        LemsunResource resource = resourceService.getBaseResource(pid.toUpperCase());

        view.addObject("resource", resource);

        return view;
    }

}
