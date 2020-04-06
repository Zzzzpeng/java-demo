package com.chen.mybatisfactories;

import com.chen.mybatisfactories.conf.AppConfig;
import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.smapper.MyUserMapper;
import com.chen.mybatisfactories.util.SpringContextUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserMapper bean = SpringContextUtils.getApplicationContext().getBean(UserMapper.class);
        System.out.println(bean.getOne());
        MyUserMapper bean1 = SpringContextUtils.getApplicationContext().getBean(MyUserMapper.class);
        System.out.println(bean1.getOne());
    }
}
