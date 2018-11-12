package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
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
@RequestMapping("guru")
public class GuruController {

    @Autowired
    private GuruService guruService;

    @ResponseBody
    @RequestMapping("showAll")
    public Map<String,Object> queryAll(Integer page ,Integer rows){
        Map<String, Object> result = new HashMap<>();
        List<Guru> byPage = guruService.findByPage(page, rows);
        Long total = guruService.findTotal();
        result.put("rows",byPage);
        result.put("total",total);
        return result;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map<String,Object> delete(String id){
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            guruService.removeGuru(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return  result;
    }

    @ResponseBody
    @RequestMapping("add")
    public Map<String,Object> add(MultipartFile file, Guru guru, HttpSession session){

        HashMap<String, Object> reslult = new HashMap<>();
        try{
            String uitils = Download.uitils(file, "/back/img/guru", session);
            guru.setHeadPic(uitils);
            guruService.addGuru(guru);
            reslult.put("success",true);
        }catch(Exception e){
            e.printStackTrace();
            reslult.put("success",false);
            reslult.put("message","操作失败，请稍后再试。。。。");
        }
        return reslult;
    }
    @ResponseBody
    @RequestMapping("queryAll")
    public List<Guru> queryAll(){
        List<Guru> gurus = guruService.findAll();
        return gurus;
    }
}
