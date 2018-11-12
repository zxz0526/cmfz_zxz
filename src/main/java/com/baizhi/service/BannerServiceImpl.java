package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> queryByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return bannerDAO.queryByPage(start,rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Long queryTotal() {
        return bannerDAO.queryTotal();
    }

    public void update(Banner banner) {
        bannerDAO.update(banner);
    }

    public void add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setCreatedate(new Date());
        bannerDAO.add(banner);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Banner queryById(String id) {
        return bannerDAO.queryById(id);
    }

    @Override
    public void delete(String id) {
        bannerDAO.delete(id);
    }
}
