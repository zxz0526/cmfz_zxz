package com.baizhi.service;

import com.baizhi.entity.User;

public interface UserService {
    User findOne(String username);
    User login(User user);
    void register(User user);
    void motifyUser(User user);
}
