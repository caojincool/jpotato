package com.dp.baobao.service.impl;

import com.dp.baobao.domain.Forum;
import com.dp.baobao.dao.CategoryDao;
import com.dp.baobao.dao.ForumDao;
import com.dp.baobao.domain.Category;
import com.dp.baobao.service.IForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
@Service
public class ForumService implements IForumService {


    private ForumDao forumDao;


    private CategoryDao categoryDao;

    @Autowired
    public ForumService(ForumDao forumDao, CategoryDao categoryDao) {
        this.forumDao = forumDao;
        this.categoryDao = categoryDao;
    }

    public void addForum(Forum forum) {
        if (StringUtils.isEmpty(forum.getName()))
            throw new RuntimeException("不能缺少中文栏目名称");
        if (StringUtils.isEmpty(forum.getNameEn()))
            throw new RuntimeException("不能缺少英文栏目名称");
        forumDao.insert(forum);
    }

    public Forum getForum(UUID id) {
        return forumDao.getForum(id.toString());
    }

    public void updateForum(Forum forum) {
        if (forum.getId() == null)
            throw new RuntimeException("不能缺少栏目编码");
        if (StringUtils.isEmpty(forum.getName()))
            throw new RuntimeException("不能缺少中文栏目名称");
        if (StringUtils.isEmpty(forum.getNameEn()))
            throw new RuntimeException("不能缺少英文栏目名称");
        forumDao.updateForum(forum);
    }

    public void removeForum(UUID forumID) {
        forumDao.deleteForum(forumID.toString());
    }

    public List<Forum> getForums() {
        return forumDao.queryAll();
    }

    public void addCategory(Category category) {
        if (category.getForumId() == null)
            throw new RuntimeException("请选择该类别所属的栏目");
        if (StringUtils.isEmpty(category.getName()))
            throw new RuntimeException("请输入中文类别名称");
        if (StringUtils.isEmpty(category.getNameEn()))
            throw new RuntimeException("请输入英文类别名称");
        if (category.getCategoryType() == null)
            category.setCategoryType(Category.CategoryType.ARTICLE);

        categoryDao.insert(category);
    }

    public void removeCategory(UUID id) {
        if (id == null)
            throw new RuntimeException("请选择要删除的类别编码");
        categoryDao.delete(id);
    }

    public void updateCategory(Category category) {
        if (category.getId() == null)
            throw new RuntimeException("请选择要修改类别编码");
        if (category.getForumId() == null)
            throw new RuntimeException("请选择该类别所属的栏目");
        if (StringUtils.isEmpty(category.getName()))
            throw new RuntimeException("请输入中文类别名称");
        if (StringUtils.isEmpty(category.getNameEn()))
            throw new RuntimeException("请输入英文类别名称");
        if (category.getCategoryType() == null)
            throw new RuntimeException("请选择类别类型");

        categoryDao.update(category);
    }

    public List<Category> getAllCategory() {
        return categoryDao.queryAll();
    }

    public Category getCategory(UUID id) {
        if (id == null)
            throw new RuntimeException("缺少类别编码");

        return categoryDao.get(id.toString());
    }

    public List<Category> getCategoriesByForumId(UUID forumId) {
        if (forumId == null)
            throw new RuntimeException("缺少栏目编码");
        return categoryDao.queryByForumId(forumId.toString());
    }

    public Page<Forum> getPageForum(int page,int size,Sort sort){

        if (sort==null){
            sort=new Sort(Sort.Direction.ASC,"name");
        }
        Pageable query=new PageRequest(page,size,sort);

        return forumDao.queryPage(query);
    }

    public Page<Category> getPageCategories(int page,int size,Sort sort){
        if (sort==null){
            sort=new Sort(Sort.Direction.ASC,"name");
        }

        return categoryDao.queryAllByPage(new PageRequest(page,size,sort));
    }
}
