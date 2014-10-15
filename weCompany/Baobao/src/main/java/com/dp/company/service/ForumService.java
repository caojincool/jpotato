package com.dp.company.service;

import com.dp.company.dao.ForumDao;
import com.dp.company.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
@Service
public class ForumService {

    @Autowired
    private ForumDao forumDao;

    public void addForum(Forum forum){
        forumDao.insertForum(forum);
    }

    public Forum getForum(UUID id){
        return forumDao.getForum(id.toString());
    }

    public void updateForum(Forum forum){
        forumDao.updateForum(forum);
    }

    public void deleteForum(UUID forumID){
        forumDao.deleteForum(forumID);
    }
}
