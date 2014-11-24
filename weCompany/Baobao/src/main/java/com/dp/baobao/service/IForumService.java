package com.dp.baobao.service;

import com.dp.baobao.domain.Forum;
import com.dp.baobao.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/11/12.
 */
public interface IForumService {
    void addForum(Forum forum);

    Forum getForum(UUID id);

    void updateForum(Forum forum);

    void removeForum(UUID forumID);

    List<Forum> getForums();

    void addCategory(Category category);

    void removeCategory(UUID id);

    void updateCategory(Category category);

    List<Category> getAllCategory();

    Category getCategory(UUID id);

    List<Category> getCategoriesByForumId(UUID forumId);

    Page<Category> getPageCategories(int page,int size,Sort sort);

    Page<Forum> getPageForum(int page,int size,Sort sort);
}
