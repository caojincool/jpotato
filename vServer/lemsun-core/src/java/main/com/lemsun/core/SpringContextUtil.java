package com.lemsun.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-9-18
 * Time: 上午10:36
 */
public class SpringContextUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
    private static ServletContext servletContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}


	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 这里重写了bean方法，起主要作用
	 * @param name 对象名称
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

    /**
     * 在对象容器中获取指定类型的对象模型
     * @param type 类型
     * @param <T> 类型对象
     * @return 对象
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }


    /**
     * 获取WEB 应用的上下文
     * @return
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 返回 WEB 应用的上下文
     * @param servletContext
     */
    public static void setServletContext(ServletContext servletContext) {
        SpringContextUtil.servletContext = servletContext;
    }
}
