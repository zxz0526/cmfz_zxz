package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("login")
    public String login(String code,Admin admin, HttpSession session){
        Admin login = adminService.login(admin);
        String s = (String) session.getAttribute("validationCode");
        if(s.equals(code)){
            if(login!=null){
                session.setAttribute("admin",login);
                return "redirect:/back/main/main.jsp";
            }else {
                return "redirect:/back/admin/login.jsp";
            }
        }else{
            return "redirect:/back/admin/login.jsp";
        }
    }

    @ResponseBody
    @RequestMapping("update")
    public Map<String,Object> update(HttpSession session,Admin admin,String oldpassword){
        HashMap<String, Object> maps = new HashMap<>();
        Admin admin1 = (Admin) session.getAttribute("admin");
        try{
            if(admin1.getPassword().equals(oldpassword)) {
                adminService.motify(admin);
                maps.put("success", true);
                session.removeAttribute("admin");
                session.setAttribute("admin",admin);
            }else{
                maps.put("success",false);
                maps.put("message","原密码输入有误，密码修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            maps.put("success",false);
            maps.put("message","操作失败，请稍后再试。。。。");
        }
        return maps;
    }

    @RequestMapping("out")
    public String out(HttpSession session){
        session.invalidate();
        return "redirect:/back/admin/login.jsp";
    }
}
