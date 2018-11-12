package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {


    List<Album> findByPage(Integer page,Integer rows);
    Long findTotals();
    List<Album> findAlbum();
    void remove(String id);
    void addAlbum(Album album);
}
