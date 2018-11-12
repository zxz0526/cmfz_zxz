package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
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
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String,Object> queryByPage(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        List<Album> albums = albumService.findByPage(page, rows);
        Long totals = albumService.findTotals();
        result.put("rows",albums);
        result.put("total",totals);
        return result;
    }

    @ResponseBody
    @RequestMapping("showAll")
    public List<Album> queryAll(){
        List<Album> albums = albumService.findAlbum();
        return albums;
    }

    @ResponseBody
    @RequestMapping("add")
    public Map<String,Object> add(MultipartFile file, HttpSession session, Album album){
        Map<String, Object> result = new HashMap<>();
        try{
            String uitils = Download.uitils(file, "/back/img/album", session);
            album.setCoverImg(uitils);
            albumService.addAlbum(album);
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
    public Map<String,Object> delete(String id){
        Map<String, Object> result = new HashMap<>();
        try{
            albumService.remove(id);
            chapterService.deleteAll(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }
}
