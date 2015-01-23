package com.dp.test.controller;

import com.dp.baobao.domain.Category;
import com.dp.baobao.domain.Forum;
import com.dp.baobao.service.impl.ForumService;
import com.dp.test.model.CategoryEntity;
import com.dp.test.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/11/16.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ForumService forumService;

    @RequestMapping("/index")
    public String index(){
        return "/category/index";
    }

    @RequestMapping(value = "categoryList",method = RequestMethod.POST)
    public ResponseEntity<List<CategoryEntity>> categoryList(@RequestParam(value = "page",required = false,defaultValue="0")int page,
                                                       @RequestParam(value = "rows",required = false,defaultValue = "10")int rows,
                                                       @RequestParam(value = "sort",required = false,defaultValue = "name")String sort,
                                                       @RequestParam(value = "order",required = false,defaultValue = "desc")String order){
        Sort s=new Sort(Sort.Direction.fromString(order),sort);
        Page<Category> categories=forumService.getPageCategories(page>0?page-1:page,rows,s);
        List<CategoryEntity> categoryEntities=new ArrayList<CategoryEntity>(categories.getNumberOfElements());

        for (Category c:categories){
            Forum forum=forumService.getForum(c.getForumId());
            CategoryEntity categoryEntity=new CategoryEntity(
                    c.getId().toString(),
                    forum.getId().toString(),
                    c.getName(),
                    c.getNameEn(),
                    forum.getName(),
                    c.getCategoryType().name(),
                    c.isEnabled()
            );
            categoryEntities.add(categoryEntity);
        }

        return new ResponseEntity<List<CategoryEntity>>(true,(int)categories.getTotalElements(),categoryEntities);
    }

    @RequestMapping(value = "view",method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "id",required = false)String id){
        ModelAndView view=new ModelAndView("/category/view");
        if (!StringUtils.isEmpty(id))
            view.addObject("category",forumService.getCategory(UUID.fromString(id)));
        view.addObject("forums",forumService.getForums());
        return view;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<String> save(Category category){
        if(category.getId()==null){
            forumService.addCategory(category);
        }else{
            forumService.updateCategory(category);
        }

        return new ResponseEntity<>(true,"保存成功！");
    }

    @RequestMapping(value = "remove")
    public ResponseEntity<String> remove(String ids){
        String[] ida=ids.split(",");
        for (String id:ida){
            forumService.removeCategory(UUID.fromString(id));
        }
        return new ResponseEntity<>(true,"删除成功！");
    }
}
