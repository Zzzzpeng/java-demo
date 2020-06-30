package com.chen.mybatisfactories.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class ValueBean {
    @Value("${name1:czp}")
    private String name;
    private String age;
    public void show(){
        System.out.println("name:" + name);
    }
}
