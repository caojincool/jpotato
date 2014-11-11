package com.dp.company;

import com.dp.company.domain.Category;
import com.dp.company.domain.CategoryType;
import com.dp.company.domain.Forum;
import com.dp.company.service.ForumService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
public class ForumServiceTest extends SuperTest {
    @Autowired
    private ForumService forumService;

    @Test
    public void testAddForum(){
        Forum forum=new Forum();

        forum.setName("板块4");
        forum.setNameEn("bankuaisan4");
        forum.setEnabled(true);

        forumService.addForum(forum);
        getLog().info("插入成功");
    }

    @Test
    public void testGetForum(){
        UUID id=UUID.fromString("d0f6c1ad-c1ec-41bc-870a-467e361d98a5");
        Forum forum=forumService.getForum(id);

        getLog().info("查询到：d0f6c1ad-c1ec-41bc-870a-467e361d98a4");
        getLog().info(forum.getName());
    }

    @Test
    public void testGetForums(){
        List<Forum> forums=forumService.getForums();

        for(Forum forum:forums){
            getLog().info("ID:"+forum.getId());
            getLog().info("中文:"+forum.getName());
            getLog().info("英文:"+forum.getNameEn());
            getLog().info("是否显示:"+(forum.isEnabled()?"显示":"隐藏"));
        }
    }

    @Test
    public void testUpdateForum()
    {
        Forum forum=forumService.getForum(UUID.fromString("13c2c5b9-696f-11e4-9e75-003067974107"));
        getLog().info("更改前的显示状态:"+(forum.isEnabled()?"显示":"隐藏"));
        forum.setEnabled(true);
        forumService.updateForum(forum);
        getLog().info("更改后的显示状态:"+(forum.isEnabled()?"显示":"隐藏"));
    }

    @Test
    public void testAddCategory(){
        Category category=new Category();
        category.setForumId(UUID.fromString("a46f7e21-696f-11e4-9e75-003067974107"));
        category.setName("类别4");
        category.setNameEn("leibie4");
        category.setKeyWord("类别4关键字");
        category.setContent("类别4内容");
        category.setDescription("类别4描述");
        category.setCategoryType(CategoryType.ARTICLE);
        forumService.addCategory(category);
        getLog().info("插入一条类别");
    }

    @Test
    public void testGetAll(){
        List<Category> categories=forumService.getAllCategory();

        for (Category c:categories){
            getLog().info("名称:"+c.getName());
            getLog().info("类别:"+c.getCategoryType().name());
        }
    }

    @Test
    public void testEnum(){
        CategoryType[] categoryTypes=CategoryType.values();
//        for(CategoryType c:categoryTypes){
//            getLog().info("name|"+c.name());
//            getLog().info("toString|"+c.toString());
//            getLog().info("ordinal|"+c.ordinal());
//            getLog().info(""+c);
//        }

        CategoryType c=CategoryType.valueOf("PRODUCTLIST");


        getLog().info("name|"+c.name());
        getLog().info("toString|"+c.toString());
        getLog().info("ordinal|"+c.ordinal());
        getLog().info(""+c);
    }
}
