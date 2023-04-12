package com.demo4.mapper;

import com.demo4.pojo.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> selectAll();
    Course getById(Integer id);
    Course getByName(String name);

    int add(Course course);
    int delete(Integer id);
    int update(Course course);
}
