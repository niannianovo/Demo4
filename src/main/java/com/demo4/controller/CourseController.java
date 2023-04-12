package com.demo4.controller;

import com.demo4.pojo.Course;
import com.demo4.pojo.User;
import com.demo4.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
    public class CourseController {

        @Autowired
        private CourseService courseService;

        //课程列表页面
        @RequestMapping(value = "/Course", method = RequestMethod.GET)
        public ModelAndView getCourseList(HttpServletRequest request) {
            //获取Session中的数据并分享到请求域
            User user_session = (User) request.getSession().getAttribute("USER_SESSION");
            ModelAndView mav = new ModelAndView();
            mav.addObject("courses",courseService.getCourseList());
            mav.addObject("user",user_session);
            mav.setViewName("list");
            return mav;
        }

        //新增课程
        @RequestMapping(value = "/Course", method = RequestMethod.POST)
        public String addCourse(Course course,@RequestParam("imgFile")MultipartFile file) throws IOException {
            courseService.addCourse(course,file);
            return "redirect:/Course";
        }

        //按id删除课程
        @RequestMapping(value = "/Course/{id}", method = RequestMethod.DELETE)
        public String deleteCourse(@PathVariable("id") Integer id) {
            courseService.deleteCourse(id);
            return "redirect:/Course";
        }

        //更新页面显示
        @RequestMapping(value = "/Course/{id}",method = RequestMethod.GET)
        public String updateOrigin(@PathVariable("id")Integer id, Model model) {
            Course course = courseService.getById(id);
            model.addAttribute("course",course);
            return "update";
        }

        //更新
        @RequestMapping(value = "/Course",method = RequestMethod.PUT)
        public String updateCourse(Course course,@RequestParam("imgFile")MultipartFile file) {
            courseService.updateCourse(course,file);
            return "redirect:/Course";
        }

        //添加课程名验证
        @RequestMapping(value = "/checkName",produces = "application/json;charset=utf-8")
        @ResponseBody
        public String checkName(String name) {
            if(courseService.getByName(name)!=null) {
                return "*课程已存在";
            }else {
                return "";
            }
        }
    }
