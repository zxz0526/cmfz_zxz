package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> queryByPage(Integer page,Integer rows);
    Long queryTotal();
    void update(Banner banner);
    void add(Banner banner);
    Banner queryById(String id);
    void delete(String id);
}
