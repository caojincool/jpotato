package com.lemsun.web.view;


import com.lemsun.auth.AccountException;
import com.lemsun.core.LemsunException;
import com.lemsun.core.ResourceException;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局执行中的异常
 * User: 宗旭东
 * Date: 13-3-7
 * Time: 下午3:16
 */
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURI();

        if(url.startsWith("/interface/")) {
            return returnInterfaceView(request, response, handler, ex);
        }
        else {
            return returnWebView(request, response, handler, ex);
        }

    }


    protected ModelAndView returnInterfaceView(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ResponseEntity<String> re = new ResponseEntity<>();

        ModelAndView model = new ModelAndView(re);

        return model;
    }

    protected ModelAndView returnWebView(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex == AccountException.UnAccount) {
            String ajaxTag = request.getHeader("Request-By");//Ext
            String url=request.getRequestURI();
            if(url.indexOf("/")==0){
                url=url.substring(url.indexOf("/")+1);
            }
            if(ajaxTag == null || !ajaxTag.trim().equalsIgnoreCase("Ext")){
                String targetUrl=request.getQueryString()==null?url:url+"?"+request.getQueryString();
             //  request.setAttribute("targetUrl",targetUrl);
                return new ModelAndView("redirect:/login?targetUrl="+targetUrl);
            }else{
                outExceptionMsg(request, response, (LemsunException) ex);
            }

        }else if(ex == ResourceException.ResourceIsNull){
            ModelMap mmap = new ModelMap();
            mmap.put("message",ex.getMessage());
            mmap.put("success",false);
             return new ModelAndView("redirect:/component/main/operatingResults",mmap);
            //outExceptionMsg(request, response, (LemsunException) ex);
        }

        return null;
    }

    private void outExceptionMsg(HttpServletRequest request, HttpServletResponse response, LemsunException ex) {
        LemsunException le = (LemsunException) ex;
        ResponseEntity<String> re = new ResponseEntity<>();
        re.setSuccess(false);
        re.setCode(le.getCode());
        re.setMessage(le.getMessage());
        //re.setEntity(url);
        try {
            re.render(null, request, response);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
