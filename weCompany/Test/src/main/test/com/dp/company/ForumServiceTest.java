package com.dp.company;

import com.dp.company.domain.Forum;
import com.dp.company.service.ForumService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        forum.setId(UUID.randomUUID());
        forum.setName("板块3");
        forum.setNameEn("bankuaisan");
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
    public void testDeleteForum(){

    }
}
