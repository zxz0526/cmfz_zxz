package com.baizhi.test;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumTest extends BaseTest {
    @Autowired
    private AlbumService albumService;

    @Test
    public void queryAll(){
        List<Album> all = albumService.findByPage(1,3);
        for (Album album : all) {
            System.out.println(album);
        }
    }

    @Test
    public void queryTotal(){
        Long totals = albumService.findTotals();
        System.out.println(totals);
    }
    @Autowired
    private AlbumDAO albumDAO;

    @Test
    public void queryAlbum(){
        List<Album> album = albumService.findAlbum();
        for (Album album1 : album) {
            System.out.println(album1);
        }
      /*  List<Album> albums = albumDAO.queryAll();
        System.out.println(albums);*/
    }

    @Test
    public void delete(){
        albumService.remove("2");
    }
    @Test
    public void addAlbum(){
        Album album = new Album();
        album.setTitle("米拉日巴尊者传");
        album.setCount(20);
        album.setCoverImg("/back/img/album/A-2.jpg");
        album.setStar("3");
        album.setAuthor("张澄基译注");
        album.setBroadCast("王猛师兄");
        album.setBrief("很棒");
        albumService.addAlbum(album);
    }

    @Test
    public void queryAlll(){
        List<Album> album = albumService.findAlbum();
        for (Album album1 : album) {
            System.out.println(album1);
        }
    }
}
