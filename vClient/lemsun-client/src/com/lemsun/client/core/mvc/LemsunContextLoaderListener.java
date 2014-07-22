package com.lemsun.client.core.mvc;

import com.lemsun.client.core.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 用于监听客户端的一些状态
 * User: 宗旭东
 * Date: 13-3-7
 * Time: 下午10:34
 */
public class LemsunContextLoaderListener extends ContextLoaderListener
        implements HttpSessionListener {
    private static Logger log = LoggerFactory.getLogger(LemsunContextLoaderListener.class);

    /**
     * 初始化容器监听
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        //RequestContextListener
        //服务器启动就创建依赖注入
        ApplicationContext context = getCurrentWebApplicationContext();

        Host host = context.getBean(Host.class);

        //获取服务器信息.
        if(log.isInfoEnabled())
            log.info("开始加载 WEB 客户端");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
