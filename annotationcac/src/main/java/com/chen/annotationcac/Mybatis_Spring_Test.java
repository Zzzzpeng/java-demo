package com.chen.annotationcac;

import com.chen.annotationcac.conf.MybatisConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mybatis_Spring_Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MybatisConfig.class);
//        Object test = ac.getBean("test");
//        System.out.println(test);
//        System.out.println(ac.getBean("&myMapperFactoryBean"));
//        System.out.println(ac.getBean("myMapperFactoryBean"));
//        System.out.println(ac.getBean("userMapper"));

//        UserMapper bean = ac.getBean(UserMapper.class);
//        Map postByUserId = bean.getPostByUserId(2);
//        System.out.println(postByUserId);

//        MapperScannerConfigurer mapperScannerConfigurer;

    }
}
