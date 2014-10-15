package com.dp.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dpyang on 2014/9/29.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/","home"})
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String logon(){return "login";}
}
