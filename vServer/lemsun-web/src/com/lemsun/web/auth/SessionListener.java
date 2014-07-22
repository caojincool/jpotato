package com.lemsun.web.auth;

import com.lemsun.auth.AccountManager;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 会话管理
 * User: Xudong
 * Date: 13-1-18
 * Time: 下午8:15
 */
public class SessionListener extends ContextLoaderListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();

//        if(session.getAttribute(AccountKey) == null) {
//
//        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();

        AccountManager manager = (AccountManager)session.getAttribute(AccountManager.class.getName());
        if(manager != null) {
            manager.removeSession(session.getId());
        }
    }
}
