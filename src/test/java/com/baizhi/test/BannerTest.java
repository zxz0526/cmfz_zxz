package com.baizhi.test;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BannerTest extends BaseTest {

    @Autowired
    private BannerDAO bannerDAO;
    @Autowired
    private BannerService bannerService;

    @Test
    public void queryTotal(){
        Long aLong = bannerDAO.queryTotal();
        System.out.println(aLong);
    }

    @Test
    public void queryAll(){
        List<Banner> banners = bannerService.queryByPage(1, 1);
        System.out.println(banners);
    }

    @Test
    public void add(){
        Banner banner = new Banner();
        banner.setTitle("a");
        banner.setImgPath("a");
        banner.setD_desc("a");
        banner.setStatus("y");
        banner.setCreatedate(new Date());
        bannerService.add(banner);
    }

    @Test
    public void update(){
        Banner banner = new Banner();
        banner.setId("1");
        banner.setStatus("n");
        bannerService.update(banner);
    }
}
