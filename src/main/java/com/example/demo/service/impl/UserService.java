package com.example.demo.service.impl;

import com.example.demo.pojo.User;

public interface UserService {
    //根据用户名查找用户
    User findByUserName(String username);
    // 根据用户名和密码进行注册
    void register(String username, String password);
    // 更新用户信息
    void update(User user);
    //更新用户头像
    void updateAvatar(String avatarUrl);
    //更新用户密码
    void updatePwd(String params);
}
