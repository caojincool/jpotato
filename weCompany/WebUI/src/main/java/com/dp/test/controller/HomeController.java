package com.dp.test.controller;

import com.dp.baobao.domain.Tree;
import com.dp.baobao.service.ITreeService;
import com.dp.baobao.service.IUserService;
import com.dp.baobao.service.impl.TreeService;
import com.dp.test.model.EasyUINode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dpyang on 2014/9/29.
 */
@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITreeService treeService;

    @RequestMapping(value = {"/"})
    public String index(){
        //userService.findUserByUserName("admin");
        return "/user/index";
    }

    @RequestMapping(value = "/tree")
    public String easyTree(){
        return "/tree/index";
    }

    @RequestMapping(value = "/load")
    @ResponseBody
    public List<EasyUINode> load(HttpServletResponse response,HttpServletRequest request){
        Tree tree=treeService.getRoot();

        EasyUINode node=EasyUINode.loadDate(tree);
        return node.getChildren();
    }
}
