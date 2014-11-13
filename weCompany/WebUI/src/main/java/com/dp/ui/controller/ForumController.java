package com.dp.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dp on 14-11-13.
 */
@Controller
@RequestMapping("forum")
public class ForumController {

    @RequestMapping("/index")
    public String index(){
        return "/manager/forum";
    }

}
