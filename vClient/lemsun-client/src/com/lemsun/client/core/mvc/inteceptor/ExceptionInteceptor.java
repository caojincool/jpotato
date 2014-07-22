package com.lemsun.client.core.mvc.inteceptor;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.service.ILmsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理拦截器
 * User: dp
 * Date: 13-6-7
 * Time: 上午10:44
 */
public class ExceptionInteceptor extends HandlerInterceptorAdapter {

    private Host host;

    @Autowired
    public ExceptionInteceptor(@Qualifier("host") Host host) {
        this.host = host;
    }


    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 所有的异常处理页面
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex == null) return;


        View view = Host.getApplicationContext().getBean(ILmsViewService.class).getView(Host.getInstance().getPlateform().getError());
        Map<String, Object> param = new HashMap<>();

        param.put("error", ex);

        view.render(param, request, response);

    }
}
