package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(User user){
        Map<String, Object> result = new HashMap<>();
        User one = userService.findOne(user.getUsername());
        if(one!=null){
            String salt = one.getSalt();
            String s = MD5Util.MD5(user.getPassword() + salt);
            user.setPassword(s);
            User login = userService.login(user);
            if(login!=null){
                result.put("success",true);
            }else{
                result.put("success",false);
                result.put("message","密码输入有误");
            }
        }else{
            result.put("success",false);
            result.put("message","此账号不存在");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("register")
    public Map<String,Object> register(User user){
        Map<String, Object> result = new HashMap<>();
        try{
            userService.register(user);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("update")
    public Map<String,Object> updateUser(User user){
        Map<String, Object> result = new HashMap<>();
        try{
            userService.motifyUser(user);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

}
