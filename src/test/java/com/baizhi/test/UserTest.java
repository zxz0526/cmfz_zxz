package com.baizhi.test;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void login(){
        User user = new User();
        user.setUsername("zx");
        user.setPassword("12345");
        User one = userService.findOne(user.getUsername());
        if(one!=null){
            String salt = one.getSalt();
            String s = MD5Util.MD5(user.getPassword() + salt);
            user.setPassword(s);
            User login = userService.login(user);
            if(login!=null){
                System.out.println("用户登陆成功");
            }else{
                System.out.println("密码输入有误");
            }
        }else{
            System.out.println("账号输入有误");
        }



    }

    @Test
    public void register(){
        User user = new User();
        user.setUsername("zxz");
        user.setPassword("123456");
        userService.register(user);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId("fabf6579-b72d-4be2-91b7-a9ac58827fdb");
        user.setUsername("aaa");
        user.setPassword("123");
        userService.motifyUser(user);
    }

}
