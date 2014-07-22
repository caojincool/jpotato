package com.lemsun.websocket;

import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录Socket 应该携带的模型信息
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午1:32
 */
public class SocketLogon {

    private String plateform;
    private String ip;
    private String id;
    private HttpSession session;
    private String token;

    /**
     * 创建一个登录模型
     * @param token 用户票据
     * @param plateform 登录平台
     * @param ip 登录IP
     */
    public SocketLogon(HttpSession session, String id, String token, String plateform, String ip) {
        this.id = id;
        this.plateform = plateform;
        this.session = session;
        this.ip = ip;
        this.token = token;
    }

    /**
     * 获取用户登录的唯一值
     */
    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    /**
     * 获取用户使用的平台组件
     */
    public String getPlateform() {
        return plateform;
    }

    /**
     * 获取用户登录的IP
     */
    public String getIp() {
        return ip;
    }

    public HttpSession getSession() {
        return session;
    }


    private static UrlPathHelper helper = new UrlPathHelper();

    /**
     * 使用请求对象创建一个登录对象
     * @param request HTTP 请求
     * @return 登录信息模型
     */
    public static SocketLogon createSocketLogno(HttpServletRequest request) {
        String templateUri = "/channel/{plateform}/{token}";
        UriTemplate template = new UriTemplate(templateUri);

        String url = helper.getRequestUri(request);

        Map<String, String> params = template.match(url);

        String plateform = params.containsKey("plateform") ? params.get("plateform") : null;
        String token = params.containsKey("token") ? params.get("token") : null;


        return new SocketLogon(request.getSession(), request.getRequestedSessionId(), token, plateform, request.getRemoteAddr());
    }

}
