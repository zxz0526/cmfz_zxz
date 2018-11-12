package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.util.MD5Util;
import com.baizhi.util.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findOne(String username) {
        return userDAO.queryById(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {

        return userDAO.login(user);
    }

    @Override
    public void register(User user) {
        user.setId(UUID.randomUUID().toString());
        String salt = RandomStringUtil.getRandomString(4);
        user.setSalt(salt);
        String s = MD5Util.MD5(user.getPassword() + salt);
        user.setPassword(s);
        user.setCreateDate(new Date());
        userDAO.add(user);
    }


    public void motifyUser(User user) {
        String salt = RandomStringUtil.getRandomString(4);
        user.setSalt(salt);
        String s = MD5Util.MD5(user.getPassword() + salt);
        user.setPassword(s);
        userDAO.update(user);
    }
}
