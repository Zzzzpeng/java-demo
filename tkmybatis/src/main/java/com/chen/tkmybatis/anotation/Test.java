package com.chen.tkmybatis.anotation;

import com.chen.tkmybatis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.chen.tkmybatis")
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(Test.class);
        UserService bean = ap.getBean(UserService.class);
        System.out.println(bean);
    }
}
