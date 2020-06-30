package com.chen.mybatisfactories;

import com.chen.mybatisfactories.Po.Post;
import com.chen.mybatisfactories.bean.*;
import com.chen.mybatisfactories.conf.AppConfig;
import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.mapper.apollo.AppMapper;
import com.chen.mybatisfactories.service.Engine;
import com.chen.mybatisfactories.service.GoodService;
import com.chen.mybatisfactories.service.Human;
import com.chen.mybatisfactories.service.UserService;
import com.chen.mybatisfactories.service.impl.UserServiceImpl;
import javafx.geometry.Pos;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        Engine engineFactoryBean = (Engine) ac.getBean("engineFactoryBean");
//        engineFactoryBean.fire();
//        ac.getBean(Car.class).work();
//        Object engine = ac.getBean("engine");
////        System.out.println(engine);


        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = ac.getBean("userServiceImpl",UserService.class);
        bean.update();


//        new Thread(()->bean.getOne()).start();
//        Thread.sleep(80);
//        new Thread(()->bean.update()).start();

//        //间隙锁测试,线程1区间修改,线程2去读
//        new Thread(()->bean.updateJianxi()).start();
//        Thread.sleep(1000);
//        new Thread(()->bean.getOne()).start();


        //可重复读&修改同一行数据,可重复读失效(dml语句是当前读,不再读开始版本而是读最新版本)
        new Thread(()->bean.decrece(1,2000L)).start();
        Thread.sleep(900);
        new Thread(()->bean.decrece(1,0)).start();


    }


    @org.junit.Test
    public void mybatisTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserMapper bean = ac.getBean(UserMapper.class);
        System.out.println(bean.getOne());
    }

    @org.junit.Test
    public void mybatisTransationTest() throws InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userServiceImpl");
//        bean.getOne();
        new Thread(()->bean.getOne()).start();
        Thread.sleep(200);
        new Thread(()->bean.update()).start();
        System.out.println(bean.getOne());
    }

    public static void beanFactoryMergeAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Car bean = ac.getBean(Car.class);
        bean.work();
    }
    @org.junit.Test
    public  void autoNameAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Engine bean = ac.getBean("engine",Engine.class);
        System.out.println(bean);

        UserService bean1 = (UserService) ac.getBean("userServiceImpl");
        bean1.update();
//        UserService bean = (UserService) ac.getBean("myBean");
//        bean.update();

//        System.out.println(bean == bean1);
    }

    public static void autoAopTest(){
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

    public static void springTransactionTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userService");
        bean.update();
    }

    @org.junit.Test
    public void importTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Post bean = ac.getBean(Post.class);
        System.out.println(bean);
    }

    @org.junit.Test
    public void mybatisApolloTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppMapper bean = ac.getBean(AppMapper.class);
        System.out.println(bean.getAll());
    }
    @org.junit.Test
    public void placeHolderTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ValueBean bean = ac.getBean(ValueBean.class);
        bean.show();
    }

}
