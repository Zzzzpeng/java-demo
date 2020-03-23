package com.chen.hibernatedemo.hibernatedemo;

import com.chen.hibernatedemo.hibernatedemo.dao.UserRepository;
import com.chen.hibernatedemo.hibernatedemo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernatedemoApplicationTests {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        User user = userRepository.findUserById(1L);
        System.out.println(user);
        user.setNickName("不不不");
        user = userRepository.save(user);
//        user = userRepository.findUserById(1L);
        System.out.println(user.toString());
    }

}
