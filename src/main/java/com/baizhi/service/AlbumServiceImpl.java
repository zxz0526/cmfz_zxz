package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return albumDAO.queryByPage(start,rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        return albumDAO.queryTotal();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findAlbum() {
        return albumDAO.queryAll();
    }

    public void remove(String id) {
        albumDAO.delete(id);
    }

    public void addAlbum(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setPublishDate(new Date());
        albumDAO.add(album);
    }

}
