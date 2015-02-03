package com.dp.baobao.mapper;

import com.dp.baobao.domain.*;

import java.util.List;

/**
 * 持久对象
 * Created by dpyang on 2015/1/23.
 */
public interface IBaobaoMapper {

    public void insertCompany(Company company);

    public void updateCompany(Company company);

    public Company getCompany(IQuery query);

    public List<Company> loadCompanies();

    public Forum getForum(IQuery query);

    public List<Forum> loadForumsByCompanyId(IQuery query);

    public List<Forum> loadForums();

    public void insertForum(Forum forum);

    public void updateForum(Forum forum);

    public void insertCategory(Category category);

    public void updateCategory(Category category);

    public Category getCategory(IQuery query);

    public List<Category> loadCategories();

    public List<Category> loadCategoriesByForumId(IQuery query);

    public void insertArticle(Article article);

    public void updateArticle(Article article);

    /**
     * 根据条件查询文章总数
     * @param query 查询条件
     * @return 文章总数
     */
    public int getArticleTotal(IQuery query);

    public Article getArticle(IQuery query);

    public List<Article> loadPageArticles(IQuery query);
}
