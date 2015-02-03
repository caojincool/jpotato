package com.dp.baobao.service;

import com.dp.baobao.domain.*;

import java.util.List;

/**
 * 服务接口
 * Created by dpyang on 2015/1/23.
 */
public interface IBaobaoService {

     /**
      * 编辑公司信息
      * 公司信息必须包含id
      * 否则会执行插入操作
      * @param company 公司信息
      */
     void editCompany(Company company);

     /**
      * 查询公司信息
      * @param query 实现查询接口的类
      * @return 公司信息
      */
     Company getCompany(IQuery query);

     /**
      * 加载所有公司信息
      * @return 所有的公司信息
      */
     List<Company> loadCampanies();

     /**
      * 编辑或者插入栏目信息
      * 栏目的id如果不为空 更新栏目
      * 否则就插入栏目信息
      * @param forum 栏目信息
      */
     void editForum(Forum forum);

     /**
      * 获取栏目信息
      * @param query 实现query 的查询接口
      * @return 栏目对象
      */
     Forum getForum(IQuery query);

     /**
      * 获取所有的栏目信息
      * @return 所有的栏目信息
      */
     List<Forum> loadForums();

     /**
      * 获取某个公司下的所有栏目信息
      * @param query 实现query的查询对象
      * @return 某个公司下的所有栏目信息
      */
     List<Forum> loadForumsByCompanyId(IQuery query);

     /**
      * 编辑或者插入类别信息
      * 如果类别id为空，就插入类别信息
      * 否则就更新类别信息
      * @param category 类别信息
      */
     void editCategory(Category category);

     /**
      * 获取一个类别信息
      * @param query 查询条件必须是实现query接口的类别对象
      * @return 文章类别信息
      */
     Category getCategory(IQuery query);

     /**
      * 获取所有的类别信息
      * @return 所有的类别信息
      */
     List<Category> loadCategories();

     /**
      * 获取某个栏目下的所有类别信息
      * @param query 实现query的栏目查询对象
      * @return 类别列表
      */
     List<Category> loadCategoriesByForumId(IQuery query);

     /**
      * 编辑或者插入文章信息
      * 如果文章id是空，就编辑文章
      * 否则就插入插入文章
      * @param article 文章信息
      */
     void editArticle(Article article);

     /**
      * 获取一篇文章信息
      * @param query 实现query的查询对象
      * @return 一篇文章
      */
     Article getArticle(IQuery query);

     /**
      * 获取分页的文章列表
      * @param query 实现AbstractPageQuery的查询对象
      * @return 文章分页信息
      */
     Page<Article> loadPageArticle(IQuery query);

     /**
      * 获取某个类别下的分页文章列表
      * @param query 实现AbstractPageQuery的查询对象
      * @return 文章分页信息
      */
     Page<Article> loadPageArticleByCategoryId(IQuery query);
}
