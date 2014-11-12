package com.dp.baobao.service;

import com.dp.baobao.domain.Article;

import java.util.List;
import java.util.UUID;

/**
 * Created by dpyang on 2014/11/12.
 */
public interface IArticleService {

    void addArticle(Article article);

    void editArticle(Article article);

    void removeArticle(UUID id);

    Article getArticle(UUID id);

    List<Article> getArticlesByCategory(UUID categoryId);

    void updateViewCount(UUID id);
}
