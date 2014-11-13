package com.dp.baobao.service;

import com.dp.baobao.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

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

    /**
     * 根据文章类别获取该类别下的分页信息
     * @param categoryId 类别编码不能为空
     * @param page 当前页从0开始
     * @param size 页大小
     * @param sort 如果不指定,默认是以name升序
     * @return
     */
    Page<Article> getPageByCategory(UUID categoryId,int page,int size,Sort sort);
}
