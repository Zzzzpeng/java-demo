package com.chen.bookmanager.springApplication;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory;
        BeanFactoryPostProcessor beanFactoryPostProcessor;
        BeanPostProcessor beanPostProcessor;
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Object myBean = classPathXmlApplicationContext.getBean("myBean");
        System.out.println(myBean.getClass());

    }
}
