package com.baizhi.test;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void login(){
        Admin admin = new Admin();
        admin.setName("zxz");
        admin.setPassword("123");
        Admin login = adminService.login(admin);
        System.out.println(login);
    }
    @Test
    public void update(){
        Admin admin = new Admin();
        admin.setId("1");
        admin.setPassword("123123");
        adminService.motify(admin);
    }
}
