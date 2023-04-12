package com.demo4.service;

import com.demo4.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //登录验证
    User selectByAll(User user);
    //注册验证
    User selectByName(String username);
    //注册
    int add(User user);
}
