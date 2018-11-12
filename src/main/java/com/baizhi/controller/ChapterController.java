package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.Download;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("add")
    public Map<String,Object> add(MultipartFile file, Chapter chapter, HttpSession session){
        Map<String, Object> result = new HashMap<>();
        try{
            String uitils = Download.uitils(file, "/back/album/music", session);
            chapter.setDownPath(uitils);
            chapterService.addChapter(chapter);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("delete")
    public void delete(String id){
        chapterService.delete(id);
    }

    @RequestMapping("/download")
    public void download(String fileName, String openStyle, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.根据接收的文件名去服务中指定目录读取文件
        String realPath = request.getSession().getServletContext().getRealPath("/back/album/music");
        System.out.println(realPath);
        String filenames[] = fileName.toString().split("/");
        String filename = filenames[filenames.length-1];
        //2.以文件输入流读取文件
        FileInputStream is = new FileInputStream(new File(realPath, filename));
        //2.1 设置响应头
        response.setHeader("content-disposition", openStyle+";fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        //3.获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //4.使用IOUtils工具类
        IOUtils.copy(is, os);
        //5.关流
        IOUtils.closeQuietly(is);//安静关流
        IOUtils.closeQuietly(os);
    }

}
