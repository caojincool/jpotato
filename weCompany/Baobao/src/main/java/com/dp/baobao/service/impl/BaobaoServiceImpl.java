package com.dp.baobao.service.impl;

import com.dp.baobao.domain.*;
import com.dp.baobao.mapper.IBaobaoMapper;
import com.dp.baobao.service.IBaobaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by dpyang on 2015/1/23.
 */
@Service
public class BaobaoServiceImpl implements IBaobaoService{

    @Autowired
    private IBaobaoMapper iBaobaoMapper;

    @Override
    public void editCompany(Company company) {
        if (StringUtils.isEmpty(company.getId())){
            iBaobaoMapper.insertCompany(company);
        }else{
            iBaobaoMapper.updateCompany(company);
        }
    }

    @Override
    public Company getCompany(IQuery query) {
        return iBaobaoMapper.getCompany(query);
    }

    @Override
    public List<Company> loadCampanies() {
        return iBaobaoMapper.loadCompanies();
    }

    @Override
    public void editForum(Forum forum) {
        if(StringUtils.isEmpty(forum.getId())){
            iBaobaoMapper.insertForum(forum);
        }else {
            iBaobaoMapper.updateForum(forum);
        }
    }

    @Override
    public Forum getForum(IQuery query) {
        return iBaobaoMapper.getForum(query);
    }

    @Override
    public List<Forum> loadForums() {
        return iBaobaoMapper.loadForums();
    }

    @Override
    public List<Forum> loadForumsByCompanyId(IQuery query) {
        return iBaobaoMapper.loadForumsByCompanyId(query);
    }

    @Override
    public void editCategory(Category category) {
        if(StringUtils.isEmpty(category.getId())){
            iBaobaoMapper.insertCategory(category);
        }else{
            iBaobaoMapper.updateCategory(category);
        }
    }

    @Override
    public Category getCategory(IQuery query) {
        return iBaobaoMapper.getCategory(query);
    }

    @Override
    public List<Category> loadCategories() {
        return iBaobaoMapper.loadCategories();
    }

    @Override
    public List<Category> loadCategoriesByForumId(IQuery query) {
        return iBaobaoMapper.loadCategoriesByForumId(query);
    }

    @Override
    public void editArticle(Article article) {
        if(StringUtils.isEmpty(article.getId())){
            iBaobaoMapper.insertArticle(article);
        }else{
            iBaobaoMapper.updateArticle(article);
        }
    }

    @Override
    public Article getArticle(IQuery query) {
        return iBaobaoMapper.getArticle(query);
    }

    @Override
    public Page<Article> loadPageArticle(IQuery query) {
        PageImpl<Article> pages=new PageImpl<>();
        pages.setTotal(iBaobaoMapper.getArticleTotal(query));
        pages.setContent(iBaobaoMapper.loadPageArticles(query));

        return pages;
    }

    @Override
    public Page<Article> loadPageArticleByCategoryId(IQuery query) {
        return null;
    }
}
