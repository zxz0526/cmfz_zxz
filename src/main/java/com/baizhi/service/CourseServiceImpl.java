package com.baizhi.service;

import com.baizhi.dao.CourseDAO;
import com.baizhi.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findAll() {
        return courseDAO.queryAll();
    }

    @Override
    public void removeCourse(String id) {
        courseDAO.delete(id);
    }

    @Override
    public void addCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        course.setRecordDate(new Date());
        courseDAO.add(course);
    }
}
