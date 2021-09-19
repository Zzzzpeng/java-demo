package com.chen.mybatisfactories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private UserService userService;


    @Transactional
    public void transactionTest(){
//        System.out.println(userService.getOne());
        userService.updateREQUIRES_NEW();
        userService.updateREQUIRES_NEW();
        throw new RuntimeException();
    }
}
