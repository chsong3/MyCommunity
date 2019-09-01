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

    @Override
    public void createOrUpdate(User user) {
        User userInDb=userMapper.findUserByAccountId(user.getAccountId());
        if (userInDb==null){
            //如果数据库没有该用户，则插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //如果数据库存在该用户，则更新
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateUser(user);
        }
    }

}
