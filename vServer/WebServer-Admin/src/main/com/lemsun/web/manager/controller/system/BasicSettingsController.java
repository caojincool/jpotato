package com.lemsun.web.manager.controller.system;

import com.lemsun.web.manager.controller.model.system.PropModel;
import com.lemsun.web.manager.controller.util.PropsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * 基础设置控制层
 * User: 刘晓宝
 * Date: 13-9-16
 * Time: 上午8:50
 */
@Controller
@RequestMapping("system/basicSettings")
public class BasicSettingsController {
    Logger logger = LoggerFactory.getLogger(BasicSettingsController.class);
    /**
     *调整到基础设置页面
     *
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request) {
        request.getSession().getAttribute("org.apache.catalina.filters.CSRF_NONCE");
        ModelAndView view = new ModelAndView("system/basicSettings/edit");
        request.getSession().setAttribute("head", 5);
        request.getSession().setAttribute("left", 1);
        return view;
    }

    @RequestMapping(value = "email", method = RequestMethod.GET)
    public ModelAndView email(HttpServletRequest request) {
        //request.getSession().getAttribute("org.apache.catalina.filters.CSRF_NONCE");
        ModelAndView view = new ModelAndView("system/basicSettings/email");
        request.getSession().setAttribute("head", 5);
        request.getSession().setAttribute("left", 7);
        return view;
    }

    @RequestMapping(value = "SMS", method = RequestMethod.GET)
    public ModelAndView sms(HttpServletRequest request) {
        //request.getSession().getAttribute("org.apache.catalina.filters.CSRF_NONCE");
        ModelAndView view = new ModelAndView("system/basicSettings/sms");
        request.getSession().setAttribute("head", 5);
        request.getSession().setAttribute("left", 6);
        return view;
    }

    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView doEdit(PropModel propModel) {
        Class clazz = propModel.getClass();
        Field[] f = clazz.getDeclaredFields();

        try {
            String[] name = field2Name(f);
            String[] value = field2Value(f, propModel);
            PropsUtils.writeProperties(name,value);
        } catch (Exception e) {
            if(logger.isErrorEnabled())

                logger.error("反射获取对象属性值出错",e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ModelAndView view = new ModelAndView("redirect：/manager/html/reload?path=/&org.apache.catalina.filters.CSRF_NONCE=7256CD35552EF4D2042536B189DC45BF");
        return view;
    }

    private static String[] field2Name(Field[] f) {
        String[] name = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            name[i] = f[i].getName();
        }
        return name;
    }

    private static String[] field2Value(Field[] f, Object o) throws Exception {
         String[] value = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            value[i] = (String)(f[i].get(o)==null?"":f[i].get(o));
        }
        return value;
    }

}
