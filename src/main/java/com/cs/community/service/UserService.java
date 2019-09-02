package com.cs.community.service;

import com.cs.community.model.User;

import java.util.List;

public interface UserService {
    List<User> findByToken(String token);

    void insert(User user);

    void createOrUpdate(User user);
}
