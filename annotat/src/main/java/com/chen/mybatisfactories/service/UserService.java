package com.chen.mybatisfactories.service;

import com.chen.mybatisfactories.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public int update(){
        int update;
        update = userMapper.update();
        if(true)
            throw new RuntimeException();
        return update;
    }
}
