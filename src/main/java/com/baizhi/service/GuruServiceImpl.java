package com.baizhi.service;

import com.baizhi.dao.GuruDAO;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {

    @Autowired
    private GuruDAO guruDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return guruDAO.queryByPage(start,rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotal() {
        return guruDAO.queryTotal();
    }

    public void removeGuru(String id) {
        guruDAO.delete(id);
    }

    public void addGuru(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        guruDAO.add(guru);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findAll() {
        return guruDAO.queryAll();
    }
}
