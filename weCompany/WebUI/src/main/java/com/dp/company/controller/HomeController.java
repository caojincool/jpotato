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

        return "/user/index";

    }
}
