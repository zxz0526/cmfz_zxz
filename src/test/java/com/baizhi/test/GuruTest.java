package com.baizhi.test;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GuruTest extends BaseTest {

    @Autowired
    private GuruService guruService;

    @Test
    public void findTotal(){
        Long total = guruService.findTotal();
        System.out.println(total);
    }
    @Test
    public void findByPage(){
        List<Guru> byPage = guruService.findByPage(2, 1);
        System.out.println(byPage);
    }

    @Test
    public void delete(){
        guruService.removeGuru("1");
    }
    @Test
    public void add(){
        Guru guru = new Guru();
        guru.setName("索达吉堪布");
        guru.setHeadPic("/back/img/guru/2.jpg");
        guru.setSex("男");
        guruService.addGuru(guru);
    }

    @Test
    public void queryAaaa(){
        List<Guru> all = guruService.findAll();
        for (Guru guru : all) {
            System.out.println(guru);
        }
    }

}
