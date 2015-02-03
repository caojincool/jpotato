package com.dp.baobao.controller;

import com.dp.baobao.domain.*;
import com.dp.baobao.domain.query.ArticleQuery;
import com.dp.baobao.model.ResponseEntity;
import com.dp.baobao.service.IBaobaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * Created by dpyang on 2015/2/3.
 */
@Controller
@RequestMapping(value = {"/article"})
public class ArticleController {

    @Autowired
    private IBaobaoService baobaoService;

    @RequestMapping("/index")
    public String index() {

        ArticleQuery query=new ArticleQuery();
        query.setCode("001");

        Article article=baobaoService.getArticle(query);
        return "article/index";
    }

    @RequestMapping("/page/list")
    public ResponseEntity<Page<Article>> pageList(IQuery query) {


        return new ResponseEntity<>(true, "temp");
    }

    @RequestMapping("/view")
    public ModelAndView view() {
        ModelAndView view = new ModelAndView("article/view");

        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> save() {

        return new ResponseEntity<>(true, "ok");
    }

    @RequestMapping("/forumMg")
    public String forumMg() {
        return "forum/index";
    }

    @RequestMapping("/forum/list")
    public ResponseEntity<List<Forum>> forumList() {

        return new ResponseEntity<>(true, "ok");
    }

    @RequestMapping("/from/view")
    public ModelAndView forumView() {
        ModelAndView view = new ModelAndView("forum/view");


        return view;
    }

    @RequestMapping(value = "/forum/sava", method = RequestMethod.POST)
    public ResponseEntity<String> forumSave() {

        return new ResponseEntity<>(true, "ok");
    }

    @RequestMapping("/categoryMg")
    public String categoryMg() {
        return "forum/index";
    }

    @RequestMapping("category/list")
    public ResponseEntity<List<Category>> categoryList() {

        return new ResponseEntity<>(true, "ok");
    }

    @RequestMapping("category/view")
    public ModelAndView categoryView() {
        ModelAndView view = new ModelAndView("category/view");

        return view;
    }

    @RequestMapping(value = "category/save", method = RequestMethod.POST)
    public ResponseEntity<String> categorySave() {

        return new ResponseEntity<>(true, "ok");
    }
}
