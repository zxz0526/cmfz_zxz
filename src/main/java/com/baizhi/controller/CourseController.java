package com.baizhi.controller;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseservice;

    @ResponseBody
    @RequestMapping("queryAll")
    public List<Course> queryAll(){
        List<Course> courses = courseservice.findAll();
        return courses;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(String id){
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            courseservice.removeCourse(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return  result;
    }

    @ResponseBody
    @RequestMapping("add")
    public Map<String,Object> addCourse(Course course){
        Map<String, Object> result = new HashMap<>();
        try{
            courseservice.addCourse(course);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("message","操作失败，请稍后再试。。。。");
        }
        return result;
    }

}
