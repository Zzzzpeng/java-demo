package com.chen.mybatisfactories.service.impl;

import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.service.GoodService;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class UserServiceImpl implements UserService, GoodService {
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public int update() {
        int update;
        update = userMapper.update();
        System.out.println("执行update");
        return update;
//        return 0;
    }

    @Override
    public int sale(int id, int num) {
        System.out.println("执行sale");
        return 0;
    }
}
