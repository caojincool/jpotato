package com.lemsun.web.manager.controller.system;

import com.lemsun.core.Plateform;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.system.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统平台控制器
 * User: gm
 * Date: 13-1-17
 * Time: 上午9:35
 */
@Controller
@RequestMapping("system/category")
public class CategoryListController {

    @Autowired
    private IPlateformService plateformService;

    private final static Logger log = LoggerFactory.getLogger(CategoryListController.class);

    /**
     * 获取 category为 PLATEFORM 平台视图
     *
     * @return 网页平台信息
     */
    @RequestMapping(value = {"", "/", "view"})
    public ModelAndView view(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("system/plateform/view");

        request.getSession().setAttribute("head", 5);
        request.getSession().setAttribute("left", 5);
        return view;
    }
    /**
     * 获取 category为 WPF 平台视图
     *
     * @return WPF 平台信息
     */
    @RequestMapping("/web")
    public ModelAndView getWebView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("system/plateform/web");
        Plateform pf = plateformService.getByCategory("PLATEFORM");
        view.addObject("web", pf);
        return view;
    }
    /**
     * 获取 category为 WPF 平台视图
     *
     * @return WPF 平台信息
     */
    @RequestMapping("/wpf")
    public ModelAndView getWpfView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("system/plateform/wpf");
        Plateform pf = plateformService.getByCategory("WPF");
        view.addObject("listone", pf);

        return view;
    }

    /**
     * 修改 网页 平台信息
     *
     * @param context 待修改的 网页 平台信息表单
     * @return 返回修改后的 网页 平台信息表单
     */
    @RequestMapping(value = "/web/update", method = RequestMethod.POST)
    public ModelAndView updateWebContext(CategoryModel context) throws Exception {
        if (log.isDebugEnabled()) log.debug("获取要修改的系统类型对象数据 : {}", context);

        Plateform plateform = plateformService.getByCategory(context.getCategory());

        plateformService.update(context.upatePlateform(plateform));

        return new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "system/category");
    }

    /**
     * 修改 WPF 平台信息
     *
     * @param context 待修改的 WPF 平台信息表单
     * @return 返回修改后的 WPF 平台信息表单
     */
    @RequestMapping(value = "/wpf/update", method = RequestMethod.POST)
    public ModelAndView updateWpfContext(CategoryModel context) throws Exception {
        if (log.isDebugEnabled()) log.debug("获取要修改的系统类型对象数据 : {}", context);

        Plateform plateform = plateformService.getByCategory(context.getCategory());

        plateformService.update(context.upatePlateform(plateform));

        return new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "system/category");
    }
}
