package com.lemsun.client.web.controller;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.service.ILmsViewService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 上午10:43
 */
@Controller
public class HomeController {

    @Autowired
    private Host host;

    @Autowired
    private ILmsViewService lmsViewService;

    @Autowired
    private JsonObjectMapper objectMapper;

    @RequestMapping(value = {"/", "/index"})
    public View index(HttpServletRequest request) throws Exception {

        return lmsViewService.getView("");
    }

    /**
     * 获取组件资源的附件
     *
     * @param resource 资源组件PID
     * @param attach   附件名称
     * @param type     组件文件类型
     * @return
     * @throws Exception
     */
    @RequestMapping("/{resource}/{attach}.{type}")
    public View getResourceAttachment(@PathVariable("resource") String resource,
                                      @PathVariable("attach") String attach,
                                      @PathVariable("type") String type) throws Exception {


        return lmsViewService.getViewAttach(resource, attach + "." + type);
    }

    /**
     * 根据组件编码返回组件视图
     *
     * @param pid
     */
    @RequestMapping(value = "/{pid}")
    public View getView(@PathVariable String pid) throws Exception {

        return lmsViewService.getView(pid);
    }

    /**
     * 注销
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public View signOut(HttpServletResponse response) throws Exception {

        String logout = host.getPlateform().getLogout();
        return lmsViewService.getView(logout);
    }

    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login")

    public View signOn() throws Exception {
        String logon = host.getPlateform().getLogon();
        return lmsViewService.getView(logon);
    }

    @RequestMapping(value = "/zbjh", method = RequestMethod.POST)
    public View zbjh(MultipartHttpServletRequest request){

        return lmsViewService.getView("");
    }
}