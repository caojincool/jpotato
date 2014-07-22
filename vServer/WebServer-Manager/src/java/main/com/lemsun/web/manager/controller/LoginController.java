package com.lemsun.web.manager.controller;

import com.lemsun.web.manager.model.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-11-13
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"/login"})
    public String index()
    {
        return  "login/login";
    }

    @ResponseBody()
    @RequestMapping(value = {"/loginsys"})
    public String login(LoginModel lModel)
    {
        return "OK";
    }

}
