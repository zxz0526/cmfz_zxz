package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ResponseBody
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        List<Banner> banners = bannerService.queryByPage(page, rows);
        Long aLong = bannerService.queryTotal();
        result.put("rows",banners);
        result.put("total",aLong);
        return result;
    }

    @ResponseBody
    @RequestMapping("add")

    public Map<String,Object> add(MultipartFile file, Banner banner, HttpSession session){
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            String uitils = Download.uitils(file, "/back/img/banner", session);
            banner.setImgPath(uitils);
            bannerService.add(banner);
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
    public Map<String,Object> update(Banner banner){
        Map<String, Object> result = new HashMap<>();
        try{
            bannerService.update(banner);
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
    public Banner queryOne(String id){
        Banner banner = bannerService.queryById(id);
        return banner;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map<String,Object> delete(String id){
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            bannerService.delete(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return  result;
    }

}
