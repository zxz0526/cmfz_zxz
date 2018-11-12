package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return articleDAO.queryByPage(start,rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        return articleDAO.queryTotal();
    }

    public void addArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setPublishDate(new Date());
        articleDAO.add(article);
    }

    @Override
    public void removeArticle(String id) {
        articleDAO.delete(id);
    }

    @Override
    public void motifyArticle(Article article) {
        articleDAO.update(article);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Article findOne(String id) {
        return articleDAO.queryById(id);
    }
}
