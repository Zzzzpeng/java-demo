package com.chen.mybatisfactories.bean;

import com.chen.mybatisfactories.service.Human;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.stereotype.Component;

public class MyBean implements UserService {
    @Override
    public int update() {
        System.out.println("MyBean執行update");
        return 0;
    }
}

//public class MyBean implements Human {
//    @Override
//    public void show() {
//        System.out.println("MyBean執行show");
//    }
//}