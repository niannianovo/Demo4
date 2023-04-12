package com.demo4.mapper;

import com.demo4.pojo.User;

public interface UserMapper {
    //注册用户名验证
    User selectByName(String username);
    //登录验证
    User selectByAll(User user);
    //注册
    int addUser(User user);
}
