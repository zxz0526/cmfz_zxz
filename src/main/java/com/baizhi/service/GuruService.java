package com.baizhi.service;

import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruService {
    List<Guru> findByPage(Integer page, Integer rows);
    Long findTotal();
    void removeGuru(String id);
    void addGuru(Guru guru);
    List<Guru> findAll();
}
