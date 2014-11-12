package com.dp.baobao.service.impl;

import com.dp.baobao.domain.Article;
import com.dp.baobao.dao.ArticleDao;
import com.dp.baobao.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/11/12.
 */
@Service
public class ArticleService implements IArticleService {


    private ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void addArticle(Article article){
        articleDao.insert(article);
    }

    public void editArticle(Article article){
        if(article.getId()==null)
            throw new RuntimeException("文章编码不能为空！");
        if(article.getCategoryID()==null)
            throw new RuntimeException("文章所属类别不能为空！");

        articleDao.update(article);
    }

    public void removeArticle(UUID id){
        if(id==null)
            throw new RuntimeException("被删文章编码不能为空！");
        articleDao.delete(id.toString());
    }

    public Article getArticle(UUID id){
        if(id==null)
            throw new RuntimeException("文章编码不能为空！");

        return articleDao.get(id.toString());
    }

    public List<Article> getArticlesByCategory(UUID categoryId){
        if(categoryId==null)
            throw new RuntimeException("文章所属编码不能为空！");

        return articleDao.queryByCategory(categoryId.toString());
    }

    public void updateViewCount(UUID id){
        if(id==null)
            throw new RuntimeException("文章编码不能为空！");

        articleDao.updateViewCount(id.toString());
    }
}
