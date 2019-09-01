package com.cs.community.service;

import com.cs.community.model.User;

public interface UserService {
    User findByToken(String token);

    void insert(User user);

    void createOrUpdate(User user);
}
