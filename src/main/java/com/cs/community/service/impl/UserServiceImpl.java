package com.cs.community.service.impl;

import com.cs.community.mapper.UserMapper;
import com.cs.community.model.User;
import com.cs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
