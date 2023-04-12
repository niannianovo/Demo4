package com.demo4.service;

import com.demo4.mapper.UserMapper;
import com.demo4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectByAll(User user) {
        return userMapper.selectByAll(user);
    }
    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }
    @Override
    public int add(User user) {
        return userMapper.addUser(user);
    }
}
