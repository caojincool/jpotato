package com.lemsun.web;

import com.lemsun.core.SpringContextUtil;
import org.apache.catalina.ContainerServlet;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.manager.ManagerServlet;

import org.apache.catalina.Container;
import org.apache.catalina.ContainerServlet;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Globals;
import org.apache.catalina.Host;
import org.apache.catalina.Manager;
import org.apache.catalina.Server;
import org.apache.catalina.Session;
import org.apache.catalina.Wrapper;
import org.apache.catalina.util.ContextName;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * User: 宗旭东
 * Date: 13-9-23
 * Time: 上午11:37
 */
public class TomcatManagerHostServlet extends DispatcherServlet implements ContainerServlet {

    private Context context;
    private Wrapper wrapper;
    private Host host;
    private static final Logger log = LoggerFactory.getLogger(TomcatManagerHostServlet.class);


    @Override
    public void init(ServletConfig config) throws ServletException {

        SpringContextUtil.setServletContext(config.getServletContext());
        super.init(config);
        TomcatHost hs = SpringContextUtil.getBean(TomcatHost.class);
        hs.setWrapper(wrapper);
    }


    @Override
    public Wrapper getWrapper() {
        return wrapper;
    }

    @Override
    public void setWrapper(Wrapper wrapper) {

        if(wrapper == null)
        {
            context = null;
            this.wrapper = null;
            host = null;
        }
        else {

            context = (Context) wrapper.getParent();
            host = (Host) context.getParent();
            //engine = (Engine) context.getParent();

            this.wrapper = wrapper;
            if(log.isDebugEnabled()){
            log.debug("设置环境信息");
            log.debug("BaseName:" + context.getBaseName());
            log.debug("DisplayName:" + context.getDisplayName());
            }

        }

        org.springframework.context.ApplicationContext cx = SpringContextUtil.getApplicationContext();
        log.debug(cx == null ? "NULL" : cx.getDisplayName());
    }
}
