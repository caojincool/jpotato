package com.lemsun.web.inteceptor;

import com.lemsun.auth.*;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.auth.AuthenticationException;
import com.lemsun.core.auth.Security;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.web.model.HeaderTitle;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * 对控制方法执行前, 或者执行后的准备工作<br/>
 * <p>初始化一些准备对象. 比如账号信息. 显示使用信息</p>
 * User: Xudong
 * Date: 13-1-6
 * Time: 下午1:35
 */
public class PrepareModelInteceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(PrepareModelInteceptor.class);

    private IAccountService accountService;

    private IPlateformInstanceService plateformInstanceService;

    @Autowired
    public PrepareModelInteceptor(IAccountService accountService,
                                  IPlateformInstanceService plateformInstanceService) {
        this.accountService = accountService;
        this.plateformInstanceService = plateformInstanceService;
    }


    /**
     * 获取由当前的拦截器注入在请求对象内的表头对象
     */
    public static HeaderTitle getPageHeaderTitle(HttpServletRequest request) {

        return (HeaderTitle) request.getAttribute(HeaderTitle.RequestKey);
    }


    /**
     * 获取当前请求的根目录
     */
    public static String getRootPath() {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        return (String) session.getAttribute("rootPath");
    }

    /**
     * 获取最后一次访问页面. 如果没有默认返回首页地址
     *
     * @param request 请求对象
     * @return 页面地址
     */
    public static String getLastUrl(HttpServletRequest request) {
        String url = (String) request.getSession().getAttribute("last_url");

        if (StringUtils.isEmpty(url)) {
            url = request.getRequestDispatcher("/index").toString();
        }

        return url;
    }


    /**
     * 在请求对象中获取用户对象
     */
    public static IAccount getPageAccount(HttpServletRequest request) {
        return (IAccount) request.getSession().getAttribute("account");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        if (log.isDebugEnabled())
            log.debug("控制器开始执行请求 , {}", request.getRequestURI());


        String url = request.getRequestURI();

        HttpSession session = request.getSession();

        AccountManager manager = (AccountManager) session.getAttribute(AccountManager.class.getName());

        accountService.setCurrentAccountManager(manager);

        if (url.startsWith("/interface/")) {

            if(manager == null) {
                session.setAttribute(AccountManager.class.getName(), accountService.getCurrentAccountManager());
            }


        } else {

            Security security = null;

            if (o instanceof HandlerMethod) {
                HandlerMethod method = ((HandlerMethod) o);
                security = method.getMethodAnnotation(Security.class);
            }


            if (manager == null && !url.startsWith("/login")) {

                if (security != null && security.ignore()) {
                   String value= security.value();
                    HashSet<PermissionKey>  permissionKeys=manager.getAuthentication().getPermissionKeys();
                    for (PermissionKey key:permissionKeys){
                        if(key.getKey().equalsIgnoreCase(value)){
                            switch (key.getPermission().getIndex()){
                                case 1:break;
                                case 2:
                                case 3:
                                case 4:
                                    throw AuthenticationException.PermissionNodeEx;
                            }
                        }
                    }
                } else {
                    String targetUrl=request.getQueryString()==null?url:url+"?"+request.getQueryString();
                    request.setAttribute("targetUrl",targetUrl);
                    throw AccountException.UnAccount;
                }

            }

            if (request.getSession().getAttribute("rootPath") == null) {
                request.getSession().setAttribute("rootPath",
                        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
            }

            if (request.getAttribute(HeaderTitle.RequestKey) == null) {
                request.setAttribute(HeaderTitle.RequestKey, new HeaderTitle());
            }

        }

        return true;

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HttpSession session = request.getSession();

        session.setAttribute("last_url", request.getRequestURI());
    }


}
