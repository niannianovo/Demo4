package com.demo4.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        //已登录
        if(session.getAttribute("USER_SESSION")!=null) {
            System.out.println("\n\nUSER_SESSION\n\n");
            return true;
        }
        request.setAttribute("msg","您还没有登录，请先登录！");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

}
