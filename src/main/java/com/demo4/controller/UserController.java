package com.demo4.controller;

import com.demo4.service.UserService;
import com.demo4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    //登录验证
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();
        if("".equals(username)) {
            model.addAttribute("msg","请输入用户名！");
            model.addAttribute("password", password);
            return "login";
        }else if("".equals(password)) {
            model.addAttribute("msg","请输入密码！");
            model.addAttribute("username", username);
            return "login";
        }else if(userService.selectByAll(user)==null) {
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }else {
            session.setAttribute("USER_SESSION",user);
            return "redirect:/Course";
        }
    }

    //注册
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String register(User user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        if("".equals(username)){
            model.addAttribute("msg","用户名不可为空！");
            model.addAttribute("password", password);
            return "register";
        }else if("".equals(password)) {
            model.addAttribute("msg","密码不可为空！");
            model.addAttribute("username", username);
            return "register";
        }else if(userService.selectByName(username)!=null) {
            model.addAttribute("msg", "用户已存在！");
            return "register";
        }else {
            userService.add(user);
            return "redirect:/";
        }
    }

    //退出
    @RequestMapping("/Logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
