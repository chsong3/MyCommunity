package com.cs.community.service.impl;

import com.cs.community.mapper.UserMapper;
import com.cs.community.model.User;
import com.cs.community.model.UserExample;
import com.cs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findByToken(String token) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void createOrUpdate(User user) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());//拼接
        List<User> userInDb=userMapper.selectByExample(userExample);
        if (userInDb.size()==0){
            //如果数据库没有该用户，则插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //如果数据库存在该用户，则更新
            user.setGmtModified(System.currentTimeMillis());
            UserExample userExample1=new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExampleSelective(user,userExample);//此方法只更新值不为空的数据
        }
    }

}
