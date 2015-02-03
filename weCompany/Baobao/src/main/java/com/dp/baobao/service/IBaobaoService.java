package com.dp.baobao.service;

import com.dp.baobao.domain.*;

import java.util.List;

/**
 * 服务接口
 * Created by dpyang on 2015/1/23.
 */
public interface IBaobaoService {

     void editCompany(Company company);

     Company getCompany(IQuery query);

     List<Company> loadCampanies();

     void editForum(Forum forum);

     Forum getForum(IQuery query);

     List<Forum> loadForums();

     List<Forum> loadForumsByCompanyId(IQuery query);

     void editCategory(Category category);

     Category getCategory(IQuery query);

     List<Category> loadCategories();

     List<Category> loadCategoriesByForumId(IQuery query);

     void editArticle(Article article);

     Article getArticle(IQuery query);

     Page<Article> loadPageArticle(IQuery query);

     Page<Article> loadPageArticleByCategoryId(IQuery query);
}
