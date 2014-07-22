package com.lemsun.client.core.mvc.view;


import com.lemsun.client.core.Host;
import com.lemsun.client.core.PermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 全局执行中的异常
 * User: 宗旭东
 * Date: 13-3-7
 * Time: 下午3:16
 */
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

    private Host host;


    @Autowired
    public ExceptionResolver(@Qualifier("host") Host host) {
        this.host = host;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex instanceof PermissionException)
        {
            String url = null;
            try {
                url = request.getRequestURL().append(host.getPlateform().getLogon() + "?last_url=" + URLEncoder.encode(request.getRequestURI(), "UTF-8")).toString();
            } catch (UnsupportedEncodingException e) {
                url = "";
            }

            ModelAndView view = new ModelAndView(new RedirectView(url));

            return view;
        }
        else
        {
            String url = null;
            try {
                url = request.getRequestURL().append(host.getPlateform().getError() + "?last_url=" + URLEncoder.encode(request.getRequestURI(), "UTF-8")).toString();
            } catch (UnsupportedEncodingException e) {
                url = "";
            }

            ModelAndView view = new ModelAndView(new RedirectView(url));

            return view;
        }


    }
}
