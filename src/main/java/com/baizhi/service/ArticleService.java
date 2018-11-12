package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findByPage(Integer page,Integer rows);
    Long findTotals();
    void addArticle(Article article);
    void removeArticle(String id);
    void motifyArticle(Article article);
    Article findOne(String id);

}
