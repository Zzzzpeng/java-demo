package com.chen.annotationcac.service;

import com.chen.annotationcac.bean.MySimpleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NameService {
    public NameService() {
        System.out.println("NameService construct");
    }

    @Autowired
    UserService userService;
}
