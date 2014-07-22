package com.lemsun.web.inteceptor;

import com.lemsun.core.LemsunException;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截接口通道中执行的异常
 *
 * User: 宗旭东
 * Date: 13-3-4
 * Time: 下午1:48
 */
public class InterfaceExceptionInteceptor extends HandlerInterceptorAdapter {

    private JsonObjectMapper jsonObjectMapper;

    @Autowired
    public InterfaceExceptionInteceptor(JsonObjectMapper jsonObjectMapper) {
        this.jsonObjectMapper = jsonObjectMapper;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex == null)
            return;

        if(ex instanceof Exception) {
            Exception le = (Exception) ex;

            ResponseEntity<String> re = new ResponseEntity<>();
            re.setSuccess(false);
            //re.setMessage( le.getCode());
            re.setMessage(le.getMessage());
            re.render(null, request, response);
        }

    }
}
