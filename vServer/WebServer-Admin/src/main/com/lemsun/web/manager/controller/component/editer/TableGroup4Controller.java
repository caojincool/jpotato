package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.model.HeaderTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-12
 * Time: 上午9:54
 * 4 代数据表维护, 控制器
 */
@Controller
@RequestMapping("component/tablegp4")
public class TableGroup4Controller {

    /**
     * 显示4代数据表维护
     */
    @RequestMapping(value = { "edit", "/" } , method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("component/editer/TABELGP4");
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        return view;
    }

}
