package com.lemsun.web.manager.controller;

import com.lemsun.auth.AccountException;
import com.lemsun.auth.AccountManager;
import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.web.WebSession;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * User: 宗旭东
 * Date: 13-1-6
 * Time: 上午11:20
 * 网站首页控制�
 */
@Controller
public class HomeController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPlateformInstanceService plateformInstanceService;


    /**
     * 获取返回页面
     *
     * @param current 返回的页面Load(page)里面的内
     */
    @RequestMapping(value = {"/", "/index"})
    public ModelAndView index(HttpServletRequest request,String current,String index) {
        request.getSession().setAttribute("head", 0);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("current", current == null ? "" : current);
        mav.addObject("index", index == null ? "" : index);
        return mav;
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    private  String getUrl(String url){

        return null;
    }
    /**
     * 处理登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView doLogin(String username, String password, HttpSession session, HttpServletRequest request)
            throws Exception {
        String targetUrl= request.getParameter("targetUrl");
        String current= request.getParameter("current");

        targetUrl=targetUrl!=null?targetUrl:(current!=null?"index?current="+current:"index?current=component/navigation");

        ModelAndView view =null;


        try {
            PlateformInstance instance = plateformInstanceService.getSystemInstance();
            IAccount account = accountService.getByAccount(username);

            WebSession webSession = new WebSession(request, account, instance);

            BaseAccount acc = new BaseAccount(username);
            acc.setPassword(password);

            String orgPassword = DigestUtils.md5Hex(instance.getToken() + accountService.encodePassword(acc));

            //Authentication auth = accountService.doLogin(username, password, ip);
            AccountManager manager = accountService.doLogin(webSession, username, orgPassword);

            session.setAttribute(AccountManager.class.getName(), manager);
            session.setAttribute("account", manager.getAccount());
            //view.setViewName(PrepareModelInteceptor.getLastUrl(request));
            view = new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() +targetUrl);
            view.addObject("login", username);
        } catch (Exception aex) {
            view=new ModelAndView("login");
            //密码异常
            if (aex == AccountException.PasswordEx) {
                view.addObject("message",aex.getMessage());
            } else {
                view.addObject("message","账户异常,请联系管理员!");
            }
        }
        return view;
    }

    /**
     * 注销
     */
    @RequestMapping("ExitSystem")
    public ModelAndView exit(HttpSession session) {
        AccountManager  manager=(AccountManager)session.getAttribute(AccountManager.class.getName());
        accountService.Logout(manager.getAccount().getAccount());
        ModelAndView view = new ModelAndView("redirect:login");
        return view;
    }

    /**
     * web组件编辑输入绝对路径得到的附件
     * 主要用于处理火狐浏览器在创建web组件的时候,上传图片不能及时预览的问题.
     */
    @RequestMapping(value = "{filename}.{ftype}")
    public ModelAndView getAttachFile(
            HttpServletResponse response,
            HttpServletRequest request,
            @PathVariable String filename,
            @PathVariable String ftype
    ) throws IOException {

        //亲,如果这里你发现了乱码中文,不要着急哦.一般情况下请检查你的tomcat的server.xml的
        //<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"redirectPort="8443" URIEncoding="UTF-8"/>
        // 是不是少了最后一个单词,我是说utf-8. 或者 url 有没有encode方法
        String temp= URLEncoder.encode(filename,"UTF-8")+"."+ftype;
        ModelAndView view=new ModelAndView();

        view.setViewName("redirect:"
                + PrepareModelInteceptor.getRootPath()
                + "component/webskin/add/"
                + temp);
        return view;
    }

}
