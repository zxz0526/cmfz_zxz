package com.baizhi.test;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseTest extends BaseTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void queryAll(){
        List<Course> courses = courseService.findAll();
        System.out.println(courses);
    }
}
