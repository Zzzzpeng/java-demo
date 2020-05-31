package com.chen.mybatisfactories.aop;

import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.PriorityOrdered;

import java.util.Scanner;

public class Test {
    @org.junit.Test
    public void aopTest(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aop1.xml");
        Human bean = ac.getBean("personProxied",Human.class);
        Object o = ac.getBean("personProxied");
        bean.run();
        ((Human) o).run();

//        Object o = ac.getBean(ProxyFactoryBean.class);
//        ((Human) o).run();

    }

    @org.junit.Test
    public void aopAutoTest(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aop1.xml");
        Human bean = ac.getBean(Human.class);
        bean.run();

//        Object o = ac.getBean(ProxyFactoryBean.class);
//        ((Human) o).run();

    }
}
