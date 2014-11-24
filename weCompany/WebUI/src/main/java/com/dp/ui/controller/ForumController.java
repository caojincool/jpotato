package com.dp.ui.controller;

import com.dp.baobao.domain.Forum;
import com.dp.baobao.service.impl.ForumService;
import com.dp.ui.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Created by dp on 14-11-13.
 */
@Controller
@RequestMapping("forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @RequestMapping("/index")
    public String index(){
        return "/forum/index";
    }

    @RequestMapping(value = "/forumList",method = RequestMethod.POST)
    public  ResponseEntity<List<Forum>> forumList(@RequestParam(value = "page",required = false,defaultValue="0")int page,
                                                  @RequestParam(value = "rows",required = false,defaultValue = "10")int rows,
                                                  @RequestParam(value = "sort",required = false,defaultValue = "name")String sort,
                                                  @RequestParam(value = "order",required = false,defaultValue = "desc")String order){
        Sort s=new Sort(Sort.Direction.fromString(order),sort);

        Page<Forum> forums=forumService.getPageForum(page>0?page-1:page,rows,s);
        return new ResponseEntity<List<Forum>>(true,(int)forums.getTotalElements(),forums.getContent());
    }

    @RequestMapping(value = "/view")
    public ModelAndView view(@RequestParam(value = "id",required = false)String id){
        ModelAndView view=new ModelAndView("/forum/view");

        if(!StringUtils.isEmpty(id)){
            UUID forumid=UUID.fromString(id);
            Forum forum=forumService.getForum(forumid);
            view.addObject("forum",forum);
        }

        return view;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<String> save(Forum forum){

        if (forum.getId()==null){
        forumService.addForum(forum);
        }else{
            forumService.updateForum(forum);
        }

        return new ResponseEntity<String>(true,"保存成功");
    }


    @RequestMapping(value = "/remove")
    public ResponseEntity<String> remove(String ids){
        String[] ida=ids.split(",");

        for (String id:ida){
            forumService.removeForum(UUID.fromString(id));
        }

        return new ResponseEntity<String>(true,"删除成功!");
    }
}
