package com.dp.baobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dpyang on 2014/9/29.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/imageMg")
    public String imageMg(){
        return "/picture/index";
    }

    @RequestMapping("/tree")
    public String tree(){
        return "/tree/index";
    }


}
