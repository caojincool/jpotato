package com.lemsun.web;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.member.AbstractUserSession;
import com.lemsun.core.member.IUserSession;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * Http WebSession 封装类. 主要是将在 Http 环境中的 session 封装成统一的用户 session 给用户使用.
 * User: Lucklim
 * Date: 12-12-4
 * Time: 上午9:07
 */
public class WebSession extends AbstractUserSession {
    private HttpSession session;

    public WebSession(HttpServletRequest request, IAccount account, PlateformInstance plateform) {
        super(account, plateform, request.getRemoteAddr());
        this.session = request.getSession();

    }

    /**
     * 获取一个真实的 Http WebSession
     */
    public HttpSession getHttpSession() {
        return session;
    }

    @Override
    public String getSessionId() {
        return session.getId();
    }

    @Override
    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public void close() {
        session.invalidate();
    }

}
