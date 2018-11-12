package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.util.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("showAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        List<Article> articles = articleService.findByPage(page, rows);
        Long totals = articleService.findTotals();
        result.put("rows",articles);
        result.put("total",totals);
        return result;
    }

    @ResponseBody
    @RequestMapping("add")
    public Map<String,Object> add(MultipartFile file, Article article, HttpSession session){
        HashMap<String, Object> reslult = new HashMap<>();
        try{
            String uitils = Download.uitils(file, "/back/img/article", session);
            article.setImgPath(uitils);
            articleService.addArticle(article);
            reslult.put("success",true);
        }catch(Exception e){
            e.printStackTrace();
            reslult.put("success",false);
            reslult.put("message","操作失败，请稍后再试。。。。");
        }
        return reslult;
    }


    @ResponseBody
    @RequestMapping("delete")
    public Map<String,Object> delete(String id){
        Map<String, Object> result = new HashMap<>();
        try {
            articleService.removeArticle(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("update")
    public Map<String,Object> update(MultipartFile file, Article article,HttpSession session ){
        HashMap<String, Object> result = new HashMap<>();
        try{
            String uitils = Download.uitils(file, "/back/img/article", session);
            article.setImgPath(uitils);
            articleService.motifyArticle(article);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("queryOne")
    public Article queryOne(String id){
        Article article = articleService.findOne(id);
        return article;
    }
}
