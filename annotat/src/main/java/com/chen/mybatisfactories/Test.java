package com.chen.mybatisfactories;

import com.chen.mybatisfactories.bean.Bean1;
import com.chen.mybatisfactories.bean.Car;
import com.chen.mybatisfactories.bean.EngineFactoryBean;
import com.chen.mybatisfactories.bean.MyBean;
import com.chen.mybatisfactories.conf.AppConfig;
import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.service.Engine;
import com.chen.mybatisfactories.service.GoodService;
import com.chen.mybatisfactories.service.Human;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) throws Exception {
//        autoAopTest();
//        autoNameAopTest();
//        beanFactoryMergeAopTest();
//        mybatisTest();
//        springTransactionTest();
//        autoAopTest();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Engine engineFactoryBean = (Engine) ac.getBean("engineFactoryBean");
        engineFactoryBean.fire();
        ac.getBean(Car.class).work();
        Object engine = ac.getBean("engine");
//        System.out.println(engine);
    }

    public static void mybatisTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserMapper bean = ac.getBean(UserMapper.class);
        bean.update();
    }
    public static void beanFactoryMergeAopTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Car bean = ac.getBean(Car.class);
        bean.work();
    }
    public static void autoNameAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean1 = (UserService) ac.getBean("userService");
        bean1.update();

        UserService bean = (UserService) ac.getBean("myBean");
        bean.update();

        System.out.println(bean == bean1);
    }

    public static void autoAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userService");
        bean.update();
        ((GoodService) bean).sale(1, 1);
    }

    public static void aopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("bookDaoProxy");
        bean.update();
        ((GoodService) bean).sale(1, 1);
    }
    public static void springTransactionTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userService");
        bean.update();
    }
}
