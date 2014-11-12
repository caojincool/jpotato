package com.dp.ui.controller;

import com.dp.baobao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dpyang on 2014/9/29.
 */
@Controller
@RequestMapping("/user")
public class HomeController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/"})
    public String index(){
        userService.findUserByUserName("admin");
        return "/user/index";
    }


}
