package com.chen.bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoWiredMethod {
    @Autowired
    public void setService(Service service){
        System.out.println("@Autowired作用于方法的测试");
        System.out.println(service);
    }

    @Autowired
    private Service service;
}
