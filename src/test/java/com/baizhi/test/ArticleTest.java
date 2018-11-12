package com.baizhi.test;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDAO articleDAO;

    @Test
    public void queryByPage(){
        List<Article> byPage = articleService.findByPage(1, 3);
        for (Article article : byPage) {
            System.out.println(article);
        }

       /* List<Article> articles = articleDAO.queryByPage(1, 1);
        System.out.println(articles);*/
    }

    @Test
    public void queryTotal(){
        Long totals = articleService.findTotals();
        System.out.println(totals);
    }

    @Test
    public void add(){
        Article article = new Article();
        article.setTitle("1");
        article.setImgPath("a");
        article.setContent("s");
        article.setA_id("a");
        articleService.addArticle(article);
    }

    @Test
    public void uodate(){
        Article article = new Article();
        article.setId("7a8fdc10-e2d8-44fb-a66c-85b4fee35617");
        article.setTitle("111111");
       articleService.motifyArticle(article);
    }

    @Test
    public void delete(){
        articleService.removeArticle("7a8fdc10-e2d8-44fb-a66c-85b4fee35617");
    }

    @Test
    public void queryOne(){
        Article one = articleService.findOne("1");
        System.out.println(one);
    }
}
