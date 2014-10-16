package com.dp.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dpyang on 2014/9/29.
 */
@Controller
@RequestMapping("/user")
public class HomeController {

    @RequestMapping(value = {"/"})
    public String index(){
        String p="http://blog.csdn.net/zrbin153/article/details/6579277";
        return "/user/index";

    }
}
